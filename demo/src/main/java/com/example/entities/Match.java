package com.example.entities;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private ArrayList<Timestamp> goalTeam1;
    private ArrayList<Timestamp> goalTeam2;
    private ArrayList<Timestamp> penaltyTeam1;
    private ArrayList<Timestamp> penaltyTeam2;
    private Timer timer;

    //Constructor
    public Match(Team team1, Team team2) {
    this.timer = new Timer();
    this.team1 = team1;
    this.team2 = team2;
    this.goalTeam1 = new ArrayList<>();
    this.goalTeam2 = new ArrayList<>();
    this.penaltyTeam1 = new ArrayList<>();
    this.penaltyTeam2 = new ArrayList<>();
    }

    public void setGoal(Team team){
       Timestamp timestamp = new Timestamp(timer);
        if(team.equals(team1)){
           goalTeam1.add(timestamp);
       } else if(team.equals(team2)) {
            goalTeam2.add(timestamp);
        } else {
            System.out.println("Error setGoal");
        }
    }

    public void testPrint(){
        System.out.println(goalTeam1.get(0));
    }

    //Set

}

