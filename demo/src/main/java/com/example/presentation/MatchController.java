package com.example.presentation;

import com.example.App;
import com.example.entities.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MatchController {
    Team team1;
    Team team2;
    @FXML
    public Button switchToTeamSelectButton;
    //timerstyring
    public Label timerLabel;
    public Button startMatchButton, pauseMatchButton, resumeMatchButton, endMatchButton;
    //hold 1 labels og buttons
    public Label team1Label, goalCounterLabel1, penaltyCounterLabel1;
    public Button addGoalButton1, subtractGoalButton1, addPenaltyButton1, subtractPenaltyButton1;
    //hold 2 label og buttons
    public Label team2Label, goalCounterLabel2, penaltyCounterLabel2;
    public Button addGoalButton2, subtractGoalButton2, addPenaltyButton2, subtractPenaltyButton2;




    @FXML
    private void switchToTeamSelect() throws IOException {
        App.setRoot("selectTeam");
    }

    public void setTeams(Team team1, Team team2) throws IOException {
        this.team1 = team1;
        this.team2 = team2;
        team1Label.setText(team1.toString());
        team2Label.setText(team2.toString());

    }
}
