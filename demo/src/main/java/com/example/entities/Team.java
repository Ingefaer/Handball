package com.example.entities;

public class Team {
    private int id, point;
    private String teamName;

    //Constructor
    public Team(int id, String teamName, int point){
        this.id = id;
        this.teamName = teamName;
        this.point = point;
    }
    //Bruges til at hente teams uden ID til liga
    public Team(String teamName, int point) {
        this(0, teamName, point);
    }
    //Bruges til oprettelse af nyt team
    public Team(String teamName){
        this(0,teamName,0);
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
    public void setTeamPoint(int point) {
        this.point = point;
    }
    public void setTeamID(int id) {
        this.id = id;
    }

    //toString
    public String toString(){
        return "TeamName: " + teamName + ", id: " + id + ", point: " + point;
    }
}
