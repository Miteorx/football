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
    Optional<Team> optionalTeam = teamRepository.findById(teamId);
    Team team = optionalTeam.get();
    Optional<Player> optionalPlayer = playerRepository.findById(playerId);
    Player player = optionalPlayer.get();

    long price = 100000L * Integer.parseInt(player.getExperience()) / Integer.parseInt(player.getYearsOld());
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
