package com.example.football.repositories;

import com.example.football.models.Player;
import com.example.football.models.Team;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends CrudRepository<Player, Long> {
  List<Player> findPlayersByTeam(Team team);
  @Query("from Player p join p.team Team where Team.id !=:id or Team.id=null")
  List<Player> findPlayersByOtherTeam(@Param("id") long id);
}
