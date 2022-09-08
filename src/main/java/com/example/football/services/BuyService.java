package com.example.football.services;

import com.example.football.models.Player;
import com.example.football.models.Team;
import com.example.football.repositories.PlayerRepository;
import com.example.football.repositories.TeamRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BuyService {

  private final TeamRepository teamRepository;
  private final PlayerRepository playerRepository;

  public BuyService(TeamRepository teamRepository,
      PlayerRepository playerRepository) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
  }

  public void offer(long teamId, long playerId) {
    Optional<Team> optional = teamRepository.findById(teamId);
    Team team = null;
    if (optional.isPresent()) {
      team = optional.get();
    }
    Optional<Player> players = playerRepository.findById(playerId);
    Player player = null;
    if (optional.isPresent()) {
      player = players.get();
    }
    long price = 100000L * player.getExperience() / player.getYearsOld();
    if (team.getBalance() >= price) {
      Team temp = player.getTeam();
      temp.setBalance(temp.getBalance() + price);
      teamRepository.save(temp);
      player.setTeam(team);
      playerRepository.save(player);
      team.setBalance(team.getBalance() - price);
      teamRepository.save(team);
    }
  }
}
