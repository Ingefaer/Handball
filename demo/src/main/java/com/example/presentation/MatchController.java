package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Match;
import com.example.entities.Team;
import com.example.entities.Timer;
import com.example.entities.Timestamp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class MatchController {
    private Team team1;
    private Team team2;
    private Timer timer = new Timer(); //opretter Timer objekt
    private Match match;

    //timerstyring
    @FXML
    private Label timerLabel;
    @FXML
    private Button startMatchButton, pauseMatchButton, resumeMatchButton, endMatchButton;
    //hold 1 labels og buttons
    @FXML
    private Label team1Label, goalCounterLabel1, penaltyCounterLabel1;
    //hold 2 label og buttons
    @FXML
    private Label team2Label, goalCounterLabel2, penaltyCounterLabel2;
    //Ur
    @FXML
    private Timeline timeline;
    //Timeline er en JavaFX-klasse, der bruges til tidsstyrede handlinger(animationer, opdateringer, spil-loops osv.).

    //Bruges i selectTeam controlleren til at overføre to objekter af teams ind i matchControlleren.
    public void setTeams(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        team1Label.setText(team1.getTeamName());
        team2Label.setText(team2.getTeamName());

        this.match = new Match(team1,team2);
    }

    @FXML
    private void initialize() {
        pauseMatchButton.setVisible(false);
        resumeMatchButton.setVisible(false);
        endMatchButton.setVisible(false);
        //Initialize metoden køres automatisk, når FXML-filen er indlæst. den bruges til opsætning
        timerLabel.setText(timer.toString());
        // sætter label til at vise starttiden 00:00 mm:ss

        timeline = new Timeline( //ny timeline oprettes
                new KeyFrame(Duration.seconds(1), e -> {
                    //keyframe siger: efter denne tid gør noget. duration angiver tiden for hver gentagelse
                    timer.count();
                    timerLabel.setText(timer.toString());
                    if (timer.getTotalSeconds() == 60) {
                        timeline.pause(); // Stop midlertidigt
                        pauseMatchButton.setVisible(false);
                        resumeMatchButton.setVisible(true);
                    }

                    }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Sæt uendeligt antal gentagelser
    }

    @FXML
    private void onStartButtonPressed() {
        timeline.play();
        startMatchButton.setVisible(false);
        pauseMatchButton.setVisible(true);
        endMatchButton.setVisible(true);
    }
    @FXML
    private void onPauseButtonPressed() {
        timeline.pause();
        pauseMatchButton.setVisible(false);
        resumeMatchButton.setVisible(true);
    }
    @FXML
    private void onResumeButtonPressed() {
        timeline.play();
        pauseMatchButton.setVisible(true);
        resumeMatchButton.setVisible(false);
    }
    @FXML
   private void onEndButtonPressed() throws IOException {
        timeline.pause();
        pauseMatchButton.setVisible(false);
        resumeMatchButton.setVisible(true);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Vil du afslutte kampen?");
        alert.setContentText("Vær sikker på at hele kampen er spillet færdig, da den ikke kan genoptages. Du vil blive returneret til menuen, og kan genfinde rapporten under kamprapporter.");
        alert.getDialogPane().setPrefHeight(200);
        if (alert.showAndWait().get() == ButtonType.OK) {
            timeline.stop();
            saveMatch();
            App.setRoot("menu");
        }
    }

    @FXML
    private void addGoalButton1Pressed() {
        match.addGoal(team1, new Timestamp(timer));
        goalCounterLabel1.setText(""+match.goalCounter(team1));
    }

    @FXML
    private void subtractGoalButton1Pressed() {
        match.removeGoal(team1);
        goalCounterLabel1.setText(""+match.goalCounter(team1));
    }

    @FXML
    private void addGoalButton2Pressed() {
        match.addGoal(team2, new Timestamp(timer));
        goalCounterLabel2.setText(""+match.goalCounter(team2));
    }

    @FXML
    private void subtractGoalButton2Pressed() {
        match.removeGoal(team2);
        goalCounterLabel2.setText(""+match.goalCounter(team2));
    }

    @FXML
    private void addPenaltyButton1Pressed() {
        match.addPenalty(team1, new Timestamp(timer));
        penaltyCounterLabel1.setText(""+match.penaltyCounter(team1));
    }

    @FXML
    private void subtractPenaltyButton1Pressed() {
        match.removePenalty(team1);
        penaltyCounterLabel1.setText(""+match.penaltyCounter(team1));
    }
    @FXML
    private void addPenaltyButton2Pressed() {
        match.addPenalty(team2, new Timestamp(timer));
        penaltyCounterLabel2.setText(""+match.penaltyCounter(team2));
    }

    @FXML
    private void subtractPenaltyButton2Pressed() {
        match.removePenalty(team2);
        penaltyCounterLabel2.setText(""+match.penaltyCounter(team2));
    }

    private void saveMatch() {
        DataLayer data = new DataLayer();
        data.insertMatch(match);
        data.insertGoalOrPenalty("goal", match.getMatchID(), match.getTeam1ID(), match.getGoalsByTeam(team1));
        data.insertGoalOrPenalty("goal", match.getMatchID(), match.getTeam2ID(), match.getGoalsByTeam(team2));
        data.insertGoalOrPenalty("penalty", match.getMatchID(), match.getTeam1ID(), match.getPenaltiesByTeam(team1));
        data.insertGoalOrPenalty("penalty", match.getMatchID(), match.getTeam2ID(), match.getPenaltiesByTeam(team2));
        match.calculatePoints();
        data.updateTeam(team1);
        data.updateTeam(team2);
    }


    @FXML
    private void switchToTeamSelect() throws IOException {
        timeline.pause();
        pauseMatchButton.setVisible(false);
        if (!startMatchButton.isVisible()) {
            resumeMatchButton.setVisible(true);
            pauseMatchButton.setVisible(false);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Vil du returnere til holdvalg?");
        alert.setContentText("Kampen gemmes ikke!");
        alert.getDialogPane().setPrefHeight(140);
        if (alert.showAndWait().get() == ButtonType.OK) {
            timeline.stop();
            App.setRoot("selectTeam");
        }
    }
    }





