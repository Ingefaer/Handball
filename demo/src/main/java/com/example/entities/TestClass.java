package com.example.entities;

public class TestClass {
    public static void main(String[] args) {
        Timer timer = new Timer(84);
        Timestamp timestamp = new Timestamp(timer);
        System.out.println(timestamp);
        Team team1 = new Team("viborg vipers", 0);
        Team team2 = new Team("holstebro hustlers", 0);
        Match match = new Match(team1,team2);
        match.setGoal(team1);
        match.testPrint();
    }
}
