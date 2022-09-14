package com.example.football.dto;

import com.example.football.models.Team;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PlayerRequest {

  @NotNull(message = "Name shouldn't be null")
  private String name;
  @NotNull(message = "Age shouldn't be null")
  @Min(18)
  @Max(60)
  private String yearsOld;
  @NotNull(message = "Experience shouldn't be null")
  @Min(0)
  @Max(1000)
  private String experience;
  private Team team;

  public PlayerRequest(String name, String yearsOld, String experience, Team team) {
    this.name = name;
    this.yearsOld = yearsOld;
    this.experience = experience;
    this.team = team;
  }

  public String getName() {
    return name;
  }

  public String getYearsOld() {
    return yearsOld;
  }

  public String getExperience() {
    return experience;
  }

  public Team getTeam() {
    return team;
  }
}
