package com.example.entities;

public class Team {
    private int id;
    private String teamName;
    private int point;

    //Constructor
    public Team(int id, String teamName, int point){
        this.id = id;
        this.teamName = teamName;
        this.point = point;
    }

    public Team(String teamName, int point){
        this(0,teamName,point);
    }
    //Get
    public int getTeamID() {
        return id;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getTeamPoint() {
        return point;
    }

    //Set
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setPoint(int point) {
        this.point = point;
    }

    public void setTeamID(int id) {
        this.id = id;
    }

    //toString
    public String toString(){
        //todo Opdater string hvis den bruges i UI
        return "TeamName: " + teamName + ", id: " + id + ", point: " + point;
    }
}
