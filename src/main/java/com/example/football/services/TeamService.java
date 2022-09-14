package com.example.football.services;

import com.example.football.dto.TeamRequest;
import com.example.football.models.Team;
import com.example.football.repositories.TeamRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public Team saveTeam(TeamRequest teamRequest){
    Team team = new Team(0, teamRequest.getName(), teamRequest.getBalance(), teamRequest.getPlayers());
    return teamRepository.save(team);
  }

  public List<Team> readAll() {
    return (List<Team>) teamRepository.findAll();
  }

  public Team read(int id) {
    return teamRepository.findById((long) id).get();
  }

  public boolean update(Team team, int id) {
    if (teamRepository.existsById((long) id)) {
      team.setId(id);
      teamRepository.save(team);
      return true;
    }
    return false;
  }

  public boolean delete(int id) {
    if (teamRepository.existsById((long) id)) {
      teamRepository.deleteById((long) id);
      return true;
    }
    return false;
  }
}
