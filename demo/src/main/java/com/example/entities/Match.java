package com.example.entities;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private ArrayList<Timestamp> goalTeam1;
    private ArrayList<Timestamp> goalTeam2;
    private ArrayList<Timestamp> penaltyTeam1;
    private ArrayList<Timestamp> penaltyTeam2;
    private int matchID;

    //Constructor
    public Match(Team team1, Team team2) {
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

    public void addGoal(Team team, Timestamp timestamp) {
        if (team.getTeamID() == team1.getTeamID()) {
            goalTeam1.add(timestamp);
        } else if (team.getTeamID() == team2.getTeamID()) {
            goalTeam2.add(timestamp);
        } else {
            System.out.println("Error addGoal");
        }
    }

    public void removeGoal(Team team) {
        if (team.getTeamID() == team1.getTeamID() && !goalTeam1.isEmpty()) {
            goalTeam1.remove(goalTeam1.size() - 1);
        } else if (team.getTeamID() == team2.getTeamID()  && !goalTeam2.isEmpty()) {
            goalTeam2.remove(goalTeam2.size() - 1);
        } else {
            System.out.println("Error removeGoal");
        }
    }

    public int goalCounter(Team team) {
        if (team.getTeamID() == team1.getTeamID()) {
            return goalTeam1.size();
        } else if (team.getTeamID() == team2.getTeamID()) {
            return goalTeam2.size();
        } else {
            System.out.println("Error goalCounter");
            return -1;
        }
    }

    public void addPenalty(Team team, Timestamp timestamp) {
        if (team.equals(team1)) {
            penaltyTeam1.add(timestamp);
        } else if (team.equals(team2)) {
            penaltyTeam2.add(timestamp);
        } else {
            System.out.println("Error addPenalty");
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

    public int penaltyCounter(Team team) {
        if (team.getTeamID() == team1.getTeamID()) {
            return penaltyTeam1.size();
        } else if (team.getTeamID() == team2.getTeamID()) {
            return penaltyTeam2.size();
        } else {
            System.out.println("Error penaltyCounter");
            return -1;
        }
    }

        //Todo: slet de her så vi ikke afleverer testting xD

        public void print () {
            //Løber ArrayListen igennem, printer hver studerende, indtil listen er slut.
            for (Timestamp timestamp : penaltyTeam1) {
                System.out.println(timestamp);


            }
        }
    }


