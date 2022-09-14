package com.example.football.dto;

import com.example.football.models.Player;
import java.util.List;
import javax.validation.constraints.NotNull;

public class TeamRequest {

  @NotNull(message = "Team's name shouldn't be null")
  private String name;
  @NotNull(message = "Team balance shouldn't be null")
  private long balance;

  private List<Player> players;

  public TeamRequest(String name, long balance,
      List<Player> players) {

    this.name = name;
    this.balance = balance;
    this.players = players;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public String getName() {
    return name;
  }

  public long getBalance() {
    return balance;
  }
}
