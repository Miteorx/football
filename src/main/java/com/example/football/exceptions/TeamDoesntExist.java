package com.example.football.exceptions;

public class TeamDoesntExist extends Exception{

  @Override
  public String toString() {
    return "Team doesn't exist";
  }
}
