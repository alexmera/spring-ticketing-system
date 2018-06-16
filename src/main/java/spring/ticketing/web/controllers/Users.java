package spring.ticketing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.ticketing.services.AppUserService;

@Controller
@RequestMapping("/users")
public class Users {

  private final AppUserService appUserService;

  public Users(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("users", appUserService.allUsers());
    return "users-list";
  }
}
