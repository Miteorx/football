package com.example.football.controllers;

import com.example.football.dto.PlayerRequest;
import com.example.football.dto.TeamRequest;
import com.example.football.models.Player;
import com.example.football.models.Team;
import com.example.football.services.BuyService;
import com.example.football.services.PlayerService;
import com.example.football.services.TeamService;
import com.example.football.services.ViewService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

  private final BuyService buyService;
  private final ViewService viewService;
  private final TeamService teamService;
  private final PlayerService playerService;

  public MainController(BuyService buyService, ViewService viewService, TeamService teamService,
      PlayerService playerService) {
    this.buyService = buyService;
    this.viewService = viewService;
    this.teamService = teamService;
    this.playerService = playerService;
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

  @PostMapping("/team/add")
  public ResponseEntity<Team> saveTeam(@RequestBody @Valid TeamRequest teamRequest) {
    return new ResponseEntity<>(teamService.saveTeam(teamRequest), HttpStatus.CREATED);
  }

  @PostMapping("/player/add")
  public ResponseEntity<Player> savePlayer(@RequestBody @Valid PlayerRequest playerRequest) {
    return new ResponseEntity<>(playerService.savePlayer(playerRequest), HttpStatus.CREATED);
  }
}
