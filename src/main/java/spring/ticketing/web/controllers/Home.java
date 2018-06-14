package spring.ticketing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Home {

  @GetMapping
  public String home(Model model) {
    model.addAttribute("h1Message", "!HOLA SPRING MVC!");
    return "home";
  }
}
