package spring.ticketing.web.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.ticketing.model.AppUser;
import spring.ticketing.model.AppUserRol;
import spring.ticketing.model.Client;
import spring.ticketing.services.AppUserService;
import spring.ticketing.services.ClientsService;
import spring.ticketing.web.model.Alerts;
import spring.ticketing.web.model.ClientCommand;

@Controller
@RequestMapping("/clients")
public class Clients {

  private final ClientsService clientsService;
  private final AppUserService appUserService;
  private final MessageSource messageSource;

  public Clients(
      ClientsService clientsService,
      AppUserService appUserService,
      MessageSource messageSource
  ) {
    this.clientsService = clientsService;
    this.appUserService = appUserService;
    this.messageSource = messageSource;
  }

  @ModelAttribute("client")
  public Client client(@PathVariable("id") Optional<Integer> id) {
    return id
        .flatMap(clientsService::findClientById)
        .orElseGet(ClientCommand::new);
  }

  @ModelAttribute("users")
  public List<AppUser> clientUsers() {
    return appUserService.allUsers()
        .stream()
        .filter(u -> u.getRol().equals(AppUserRol.CLIENT))
        .collect(Collectors.toList());
  }

  @ModelAttribute
  public Alerts alerts() {
    return new Alerts();
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("clients", clientsService.allClients());
    return "clients-list";
  }

  @GetMapping("/form")
  public String form() {
    return "client-form";
  }

  @PostMapping("/save")
  public String save(
      @Valid @ModelAttribute("client") ClientCommand client,
      BindingResult clientResult,
      @ModelAttribute Alerts alerts,
      Locale locale,
      RedirectAttributes redirectAttributes
  ) {
    if (clientResult.hasErrors()) {
      return "client-form";
    }
    if (client.getId() == null) {
      clientsService.createClient(client);
      alerts.succes(
          messageSource
              .getMessage("clients.msg.createOk", new Object[]{client.getClientName()}, locale));
    } else {
      clientsService.updateClient(client);
      alerts.succes(
          messageSource
              .getMessage("clients.msg.updateOk", new Object[]{client.getClientName()}, locale));
    }

    redirectAttributes.addFlashAttribute("alerts", alerts);
    return "redirect:/clients";
  }

  @GetMapping("/form/{id}")
  public String edit() {
    return "client-form";
  }

  @GetMapping("/delete/{id}")
  public String delete(
      @PathVariable("id") Integer id,
      @ModelAttribute Alerts alerts,
      Locale locale,
      RedirectAttributes redirectAttributes
  ) {
    Client client = clientsService.deleteClient(id);
    alerts.succes(
        messageSource
            .getMessage("clients.msg.deleteOk", new Object[]{client.getClientName()}, locale));
    redirectAttributes.addFlashAttribute("alerts", alerts);
    return "redirect:/clients";
  }
}
