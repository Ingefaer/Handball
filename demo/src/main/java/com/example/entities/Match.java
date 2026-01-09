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
    private int matchID;

    //Constructor
    public Match(Team team1, Team team2) {
        this.timer = new Timer(); //Timeren ejes af Match og er aggreret.
        this.team1 = team1;
        this.team2 = team2;
        this.goalTeam1 = new ArrayList<>();
        this.goalTeam2 = new ArrayList<>();
        this.penaltyTeam1 = new ArrayList<>();
        this.penaltyTeam2 = new ArrayList<>();
        this.matchID = 0;
    }

    //Get
    public int getTeam1ID () {
        return team1.getTeamID();
    }
    public int getTeam2ID() {
        return team2.getTeamID();
    }

    //Set

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setGoal(Team team) {
        if (team.equals(team1)) {
            goalTeam1.add(new Timestamp(timer));
        } else if (team.equals(team2)) {
            goalTeam2.add(new Timestamp(timer));
        } else {
            System.out.println("Error setGoal");
        }
    }

    public void removeGoal(Team team) {
        if (team.equals(team1) && !goalTeam1.isEmpty()) {
            goalTeam1.remove(goalTeam1.size() - 1);
        } else if (team.equals(team2)  && !goalTeam2.isEmpty()) {
            goalTeam2.remove(goalTeam2.size() - 1);
        } else {
            System.out.println("Error removeGoal");
        }
    }


    public void setPenalty(Team team) {
        if (team.equals(team1)) {
            penaltyTeam1.add(new Timestamp(timer));
        } else if (team.equals(team2)) {
            penaltyTeam2.add(new Timestamp(timer));
        } else {
            System.out.println("Error setPenalty");
        }
    }

    public void removePenalty(Team team) {
        if (team.equals(team1) && !penaltyTeam1.isEmpty()) {
            penaltyTeam1.remove(penaltyTeam1.size() - 1);
        } else if (team.equals(team2) && !penaltyTeam2.isEmpty()) {
            penaltyTeam2.remove(penaltyTeam2.size() - 1);
        } else {
            System.out.println("Error removeGoal");
        }
    }

    //Todo: slet de her så vi ikke afleverer testting xD
    public void testPrint() {
        System.out.println(goalTeam1.get(goalTeam1.size()));
    }
    public void print() {
        //Løber ArrayListen igennem, printer hver studerende, indtil listen er slut.
        for (Timestamp timestamp : goalTeam1) {
            System.out.println(timestamp);


        }
    }


}

