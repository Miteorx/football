package com.example.football.controllers;

import com.example.football.services.BuyService;
import com.example.football.services.ViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private final BuyService buyService;
  private final ViewService viewService;

  public MainController(BuyService buyService, ViewService viewService) {
    this.buyService = buyService;
    this.viewService = viewService;
  }

  @GetMapping("/")
  public String teams(Model model) {
    model.addAttribute("teams", viewService.getAllTeams());
    return "greeting";
  }

  @GetMapping("/team/{id}")
  public String teamInfo(@PathVariable(value = "id") long id, Model model) {

    model.addAttribute("teams", viewService.getTeam(id));

    model.addAttribute("players", viewService.getAllPlayers(id));

    model.addAttribute("otherPlayers", viewService.getAllOtherPlayers(id));
    return "team";
  }

  @PostMapping("/team/{id}/{playerId}")
  public String postBuy(@PathVariable(value = "id") long id,
      @PathVariable(value = "playerId") long playerId) {
    buyService.offer(id, playerId);
    return "redirect:/team/{id}";
  }
}
