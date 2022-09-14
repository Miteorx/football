package com.example.football.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private long id;
  private String name;
  private String yearsOld;
  private String experience;
  @ManyToOne
  private Team team;

  public Player() {
  }

  public Player(long id, String name, String yearsOld, String experience,
      Team team) {
    this.id = id;
    this.name = name;
    this.yearsOld = yearsOld;
    this.experience = experience;
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getYearsOld() {
    return yearsOld;
  }

  public void setYearsOld(String yearsOld) {
    this.yearsOld = yearsOld;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }
}
