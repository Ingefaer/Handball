package com.example.entities;

import com.example.data.DataLayer;

public class TestClass {
    public static void main(String[] args) {
        DataLayer dataLayer = new DataLayer();
        Team team1 = new Team("viborg vipers");
        Team team2 = new Team("holstebro hustlers");



        Match match = new Match(team1, team2);

        dataLayer.insertMatch(match);
    }
}
