package com.example.presentation;

import com.example.App;
import com.example.entities.Match;
import com.example.entities.Team;
import com.example.entities.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    public ListView goalTeam1ListView, penaltyTeam1ListView, goalTeam2ListView, penaltyTeam2ListView;
    public Label team1Label, team2Label, scoreLabel;
    private Match match=new Match();
    private int goalsTeam1, goalsTeam2;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToSelectReport() throws IOException {
        App.setRoot("selectReport");
    }

    public void importSelectedMatch(Match match)  throws IOException {
        this.match = match;
        System.out.println(match);
        team1Label.setText(match.getTeamName(1));
        team2Label.setText(match.getTeamName(2));
        //scoreLabel som skal vises kampens resultat
        updateGoalListView(goalTeam1ListView, 1);
        updateGoalListView(goalTeam2ListView, 2);
        updatePenaltyListView(penaltyTeam1ListView, 1);
        updatePenaltyListView(penaltyTeam2ListView, 2);
        scoreLabel.setText(goalsTeam1 + " - " +  goalsTeam2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void updateGoalListView(ListView<Timestamp> list, int teamNumber) {
        ArrayList<Timestamp> timestamps;
        if (teamNumber == 1) {
         timestamps = match.getGoalsByTeam(match.getMatchID(), match.getTeam1ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
            goalsTeam1 = oList.size();
        } else if (teamNumber == 2){
            timestamps = match.getGoalsByTeam(match.getMatchID(), match.getTeam2ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
            goalsTeam2 = oList.size();
        } else {
            System.out.println("fejl");
        }

    }
    private void updatePenaltyListView(ListView<Timestamp> list, int teamNumber) {
        ArrayList<Timestamp> timestamps;
        if (teamNumber == 1) {
            timestamps = match.getPenaltiesByTeam(match.getMatchID(), match.getTeam1ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
        } else if (teamNumber == 2){
            timestamps = match.getPenaltiesByTeam(match.getMatchID(), match.getTeam2ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
        } else {
            System.out.println("fejl");
        }
    }
}
