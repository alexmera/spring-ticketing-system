package spring.ticketing.web.controllers;

import java.util.Locale;
import java.util.Optional;
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
import spring.ticketing.model.data.AppUserData;
import spring.ticketing.services.AppUserService;
import spring.ticketing.web.model.Alerts;
import spring.ticketing.web.model.AppUserDraftCommand;
import spring.ticketing.web.model.SelectOptions;

@Controller
@RequestMapping("/users")
public class Users {

  private final AppUserService appUserService;
  private final MessageSource messageSource;

  public Users(AppUserService appUserService, MessageSource messageSource) {
    this.appUserService = appUserService;
    this.messageSource = messageSource;
  }

  @ModelAttribute("user")
  public AppUser user(@PathVariable("id") Optional<Integer> id) {
//    if(id.isPresent()) {
//      Optional<AppUser> appUserOptional = appUserService.findUserById(id.get());
//      if(appUserOptional.isPresent()) {
//        return appUserOptional.get();
//      }
//      return new AppUserData();
//    }
//    return new AppUserData();
    return id
        .flatMap(appUserService::findUserById)
        .orElseGet(AppUserData::new);
  }

  @ModelAttribute("userDraft")
  public AppUserDraftCommand userDraft() {
    return new AppUserDraftCommand();
  }

  @ModelAttribute
  public Alerts alerts() {
    return new Alerts();
  }

  @ModelAttribute("userRoles")
  public SelectOptions userRoles() {
    return new SelectOptions(AppUserRol.class);
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("users", appUserService.allUsers());
    return "users-list";
  }

  @GetMapping("/form")
  public String form() {
    return "user-create-form";
  }

  @PostMapping("/create")
  public String create(
      @Valid @ModelAttribute("userDraft") AppUserDraftCommand user,
      BindingResult userResult,
      @ModelAttribute Alerts alerts,
      Locale locale,
      RedirectAttributes redirectAttributes
  ) {
    if (userResult.hasErrors()) {
      return "user-create-form";
    }
    appUserService.createUser(user, user.getPassword());
    alerts.succes(
        messageSource.getMessage("users.msg.createOk", new Object[]{user.getUserName()}, locale));

    redirectAttributes.addFlashAttribute("alerts", alerts);
    return "redirect:/users";
  }

  @PostMapping("/save")
  public String save(
      @Valid @ModelAttribute("user") AppUserData user,
      BindingResult userResult,
      @ModelAttribute Alerts alerts,
      Locale locale,
      RedirectAttributes redirectAttributes
  ) {
    if (userResult.hasErrors()) {
      return "user-form";
    }
    appUserService.updateUser(user);
    alerts.succes(
        messageSource.getMessage("users.msg.updateOk", new Object[]{user.getUserName()}, locale));

    redirectAttributes.addFlashAttribute("alerts", alerts);
    return "redirect:/users";
  }

  @GetMapping("/form/{id}")
  public String edit() {
    return "user-form";
  }

  @GetMapping("/delete/{id}")
  public String delete(
      @PathVariable("id") Integer id,
      @ModelAttribute Alerts alerts,
      Locale locale,
      RedirectAttributes redirectAttributes
  ) {
    AppUser user = appUserService.deleteUser(id);
    alerts.succes(
        messageSource
            .getMessage("users.msg.deleteOk", new Object[]{user.getUserName()}, locale));
    redirectAttributes.addFlashAttribute("alerts", alerts);
    return "redirect:/users";
  }
}
