package spring.ticketing.web.controllers;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.ticketing.model.AppUser;
import spring.ticketing.model.AppUserRol;
import spring.ticketing.model.Client;
import spring.ticketing.model.Resolution;
import spring.ticketing.model.Ticket;
import spring.ticketing.model.TicketChannel;
import spring.ticketing.model.TicketStatus;
import spring.ticketing.services.AppUserService;
import spring.ticketing.services.ClientsService;
import spring.ticketing.services.TicketsService;
import spring.ticketing.web.model.SelectOptions;
import spring.ticketing.web.model.TicketDraftCommand;
import spring.ticketing.web.model.TicketUpdateCommand;

@Controller
@RequestMapping("/tickets")
public class Tickets {

  private TicketsService ticketsService;

  private AppUserService appUserService;

  private ClientsService clientsService;

  public Tickets(
      TicketsService ticketsService,
      AppUserService appUserService,
      ClientsService clientsService
  ) {
    this.ticketsService = ticketsService;
    this.appUserService = appUserService;
    this.clientsService = clientsService;
  }

  @ModelAttribute("authUser")
  public AppUser authAppUser(Principal principal) {
    if (principal != null) {
      return appUserService.findUserByUserName(principal.getName());
    }
    return null;
  }

  @ModelAttribute("statuses")
  public SelectOptions statuses() {
    return new SelectOptions(TicketStatus.class);
  }

  @ModelAttribute("resolutions")
  public SelectOptions resolutions() {
    return new SelectOptions(Resolution.class);
  }

  @ModelAttribute("channels")
  public SelectOptions channels() {
    return new SelectOptions(TicketChannel.class);
  }

  @ModelAttribute("clients")
  public List<Client> clients() {
    return clientsService.allClients();
  }

  @GetMapping
  public String tickets(@ModelAttribute("authUser") AppUser authUser, Model model) {
    Integer operatorId = authUser.getRol().equals(AppUserRol.COORDINATOR) ? null : authUser.getId();
    Page<Ticket> tickets = ticketsService.findTickets(operatorId, null, null, null);
    model.addAttribute("tickets", tickets);
    return "tickets-list";
  }

  @GetMapping("/report")
  public String reportForm(@ModelAttribute("ticketDraft") TicketDraftCommand ticketDraft) {
    return "report-form";
  }

  @PostMapping("/report")
  public String report(
      @ModelAttribute("authUser") AppUser authUser,
      @Valid @ModelAttribute("ticketDraft") TicketDraftCommand ticketDraft,
      BindingResult result,
      Model model
  ) {
    if (result.hasErrors()) {
      return "report-form";
    }
    Ticket ticket = ticketsService.reportTicket(ticketDraft, authUser.getId());
    model.addAttribute("id", ticket.getId());
    return "redirect:/tickets/update/{id}";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable("id") Integer id, Model model) {
    Ticket ticket = ticketsService.findTicketById(id).get();
    TicketUpdateCommand ticketUpdate = TicketUpdateCommand.from(ticket);
    model.addAttribute("ticketUpdateDraft", ticketUpdate);
    return "ticket-update";
  }

  @PostMapping("/update")
  public String update(
      @ModelAttribute("authUser") AppUser authUser,
      @Valid @ModelAttribute("ticketUpdateDraft") TicketUpdateCommand ticketUpdate,
      BindingResult result
  ) {
    if (result.hasErrors()) {
      return "ticket-update";
    }
    System.out.println(ticketUpdate);
    ticketsService.updateTicket(
        ticketUpdate.getId(),
        ticketUpdate.getClient().getId(),
        ticketUpdate.getChannel(),
        ticketUpdate.getContactName(),
        ticketUpdate.getSubject(),
        ticketUpdate.getDescription()
    );
    return "redirect:/tickets";
  }

  @GetMapping("/details/{id}")
  public String details(@PathVariable("id") Integer id, Model model) {
    Ticket ticket = ticketsService.findTicketById(id).get();
    model.addAttribute("ticket", ticket);
    return "ticket-detail";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id) {
    ticketsService.deleteTicket(id);
    return "redirect:/tickets";
  }
}
