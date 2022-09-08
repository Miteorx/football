package com.example.football.services.exceptions;

public class TeamDoesntExist extends Exception{

  @Override
  public String toString() {
    return "Team doesn't exist";
  }
}
