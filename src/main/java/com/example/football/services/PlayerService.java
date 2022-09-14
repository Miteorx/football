package com.example.football.services;

import com.example.football.dto.PlayerRequest;
import com.example.football.models.Player;
import com.example.football.repositories.PlayerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerRepository playerRepository;

  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public Player savePlayer(PlayerRequest playerRequest) {
    Player player = new Player(0, playerRequest.getName(),playerRequest.getYearsOld(), playerRequest.getExperience(), playerRequest.getTeam());
    return playerRepository.save(player);
  }

  public List<Player> readAll() {
    return (List<Player>) playerRepository.findAll();
  }

  public Player read(int id) {
    return playerRepository.findById((long) id).get();
  }

  public boolean update(Player player, int id) {
    if (playerRepository.existsById((long) id)) {
      player.setId(id);
      playerRepository.save(player);
      return true;
    }
    return false;
  }

  public boolean delete(int id) {
    if (playerRepository.existsById((long) id)) {
      playerRepository.deleteById((long) id);
      return true;
    }
    return false;
  }
}
