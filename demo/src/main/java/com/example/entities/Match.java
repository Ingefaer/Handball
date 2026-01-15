package com.example.entities;

import com.example.data.DataLayer;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private ArrayList<Timestamp> goalTeam1;
    private ArrayList<Timestamp> goalTeam2;
    private ArrayList<Timestamp> penaltyTeam1;
    private ArrayList<Timestamp> penaltyTeam2;
    private int matchID;
    DataLayer data = new DataLayer();

    //Constructor
    public Match(int matchID, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.goalTeam1 = new ArrayList<>();
        this.goalTeam2 = new ArrayList<>();
        this.penaltyTeam1 = new ArrayList<>();
        this.penaltyTeam2 = new ArrayList<>();
        this.matchID = matchID;
    }

    public Match() {
        this(new Team("team"), new Team("team"));
    }

    public Match(Team team1, Team team2) {
        this(0, team1, team2);

    }

    //Get
    public int getTeam1ID () {
        return team1.getTeamID();
    }
    public int getTeam2ID() {
        return team2.getTeamID();
    }
    public int getMatchID() {return matchID;}


    //Set

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void addGoal(Team team, Timestamp timestamp) {
        if (team.equals(team1)) {
            goalTeam1.add(timestamp);
        } else if (team.equals(team2)) {
            goalTeam2.add(timestamp);
        } else {
            System.out.println("Error addGoal");
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

    public int goalCounter(Team team) {
        if (team.equals(team1)) {
            return goalTeam1.size();
        } else if (team.equals(team2)) {
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
            System.out.println("Error removePenalty");
        }
    }

    public int penaltyCounter(Team team) {
        if (team.equals(team1)) {
            return penaltyTeam1.size();
        } else if (team.equals(team2)) {
            return penaltyTeam2.size();
        } else {
            System.out.println("Error penaltyCounter");
            return -1;
        }
    }
    private void calculatePoints() {
        if (goalTeam1.size()>goalTeam2.size()) {
            int team1points = team1.getTeamPoint();
            team1.setTeamPoint(team1points + 2);

        } else if (goalTeam2.size()>goalTeam1.size()) {
            int team2points = team2.getTeamPoint();
            team2.setTeamPoint(team2points + 2);
        }
        else if (goalTeam1.size()==goalTeam2.size()) {
            int team1points = team1.getTeamPoint();
            team1.setTeamPoint(team1points + 1);

            int  team2points = team2.getTeamPoint();
            team2.setTeamPoint(team2points + 1);
        } else {
            System.out.println("Error setPoints");
        }
    }
    public void saveMatch(Match match) {
        DataLayer data = new DataLayer();
        data.insertMatch(match);
        data.insertGoalOrPenalty("goal", matchID, getTeam1ID(), goalTeam1);
        data.insertGoalOrPenalty("goal", matchID, getTeam2ID(), goalTeam2);
        data.insertGoalOrPenalty("penalty", matchID, getTeam1ID(), penaltyTeam1);
        data.insertGoalOrPenalty("penalty", matchID, getTeam2ID(), penaltyTeam2);
        calculatePoints();
        data.updateTeam(team1);
        data.updateTeam(team2);
    }
    public ArrayList<Match> getAllMatches() {
        return data.getAllMatches();
    }

    public ArrayList<Timestamp> getGoalsByTeam(int matchID, int teamID) {
        return data.getGoals(matchID,teamID);
    }
    public ArrayList<Timestamp> getPenaltiesByTeam(int matchID, int teamID) {
        return data.getPenalties(matchID, teamID);
    }

    public String getTeamName(Team team) {
        return team.getTeamName();
    }

    public String getTeamName(int teamNumber) {
        if (teamNumber == 1) {
            return team1.getTeamName();
        } else if (teamNumber == 2) {
            return  team2.getTeamName();
        }
        else {
            return null;
        }
    }

    public String toString(){
        return "Match ID: " + matchID + " team1: " + team1 + " team2: " + team2;
    }



}


