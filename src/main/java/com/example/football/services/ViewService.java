package com.example.football.services;

import com.example.football.models.Player;
import com.example.football.models.Team;
import com.example.football.repositories.PlayerRepository;
import com.example.football.repositories.TeamRepository;
import com.example.football.exceptions.TeamDoesntExist;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

  private final PlayerRepository playerRepository;
  private final TeamRepository teamRepository;

  public ViewService(PlayerRepository playerRepository,
      TeamRepository teamRepository) {
    this.playerRepository = playerRepository;
    this.teamRepository = teamRepository;
  }

  public List<Player> getAllPlayers(long id) {
    return playerRepository.findPlayersByTeam(getTeam(id));
  }

  public List<Player> getAllOtherPlayers(long id) {
    return playerRepository.findPlayersByOtherTeam(getTeam(id).getId());
  }

  public Team getTeam(long id) {
    try {
      if (!teamRepository.existsById(id)) {
        throw new TeamDoesntExist();
      }
      Optional<Team> optional = teamRepository.findById(id);
      Team teams = null;
      if (optional.isPresent()) {
        teams = optional.get();
      }
      return teams;
    } catch (TeamDoesntExist e) {
      e.printStackTrace();
      return null;
    }
  }

  public Iterable<Team> getAllTeams() {
    return teamRepository.findAll();
  }

}
