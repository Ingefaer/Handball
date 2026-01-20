package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Match;
import com.example.entities.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReportController {
    private DataLayer data = new DataLayer();
    @FXML
    private ListView goalTeam1ListView, penaltyTeam1ListView, goalTeam2ListView, penaltyTeam2ListView;
    @FXML
    private Label team1Label, team2Label, scoreLabel;
    private Match match = new Match();
    private int totalGoalsTeam1, totalGoalsTeam2;

    @FXML
    private void switchToSelectReport() throws IOException {
        App.setRoot("selectReport");
    }

    //bruges når report controlleren skal importere den match som skal vises i rapporten, fra selectReportController
    public void importSelectedMatch(Match match) {
        this.match = match;
        team1Label.setText(match.getTeamName(1));
        team2Label.setText(match.getTeamName(2));
        //scoreLabel som skal vises kampens resultat
        updateGoalListView(goalTeam1ListView, 1);
        updateGoalListView(goalTeam2ListView, 2);
        updatePenaltyListView(penaltyTeam1ListView, 1);
        updatePenaltyListView(penaltyTeam2ListView, 2);
        scoreLabel.setText(totalGoalsTeam1 + " - " +  totalGoalsTeam2);
    }

    //PRE teamnumber must be 1 or 2
    private void updateGoalListView(ListView<Timestamp> list, int teamNumber) {
        ArrayList<Timestamp> timestamps;
        if (teamNumber == 1) {
         timestamps = data.getGoals(match.getMatchID(), match.getTeam1ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
            totalGoalsTeam1 = oList.size();
        } else if (teamNumber == 2){
            timestamps = data.getGoals(match.getMatchID(), match.getTeam2ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
            totalGoalsTeam2 = oList.size();
        } else {
            System.out.println("Error: update goal listview");
        }
    }

    //PRE teamnumber must be 1 or 2
    private void updatePenaltyListView(ListView<Timestamp> list, int teamNumber) {
        ArrayList<Timestamp> timestamps;
        if (teamNumber == 1) {
            timestamps = data.getPenalties(match.getMatchID(), match.getTeam1ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
        } else if (teamNumber == 2){
            timestamps = data.getPenalties(match.getMatchID(), match.getTeam2ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
        } else {
            System.out.println("Error: update penalty listview");
        }
    }

    private String toCSV() {
        //arraylists med mål og penalties hentes
        ArrayList<Timestamp> goals1 = data.getGoals(match.getMatchID(), match.getTeam1ID());
        ArrayList<Timestamp> goals2 = data.getGoals(match.getMatchID(), match.getTeam2ID());
        ArrayList<Timestamp> penalties1 = data.getPenalties(match.getMatchID(), match.getTeam1ID());
        ArrayList<Timestamp> penalties2 = data.getPenalties(match.getMatchID(), match.getTeam2ID());

        // læser hver entitet i arraylisterne og samler inholdet(Timestamps) i en tekststreg og sætter semikolon mellem hver timestamp
        String goal1 = goals1.stream().map(Timestamp::toString).collect(Collectors.joining(";"));
        String goal2 = goals2.stream().map(Timestamp::toString).collect(Collectors.joining(";"));
        String penalty1 = penalties1.stream().map(Timestamp::toString).collect(Collectors.joining(";"));
        String penalty2 = penalties2.stream().map(Timestamp::toString).collect(Collectors.joining(";"));

        //returnerer det hele i en teksstreng, backslash er for at bibeholde" og semicolon laver celleopdeling
        return match.getMatchID() + ";" +
                "\"" + match.getTeamName(1) + "\";" +
                "\"" + goal1 + "\";" +
                "\"" + penalty1 + "\";" +
                "\"" + match.getTeamName(2) + "\";" +
                "\"" + goal2 + "\";" +
                "\"" + penalty2 + "\";";
    }

    //Indholdet skrives ind i filen
    private void exportMatchToCSV(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("MatchID, team1, goalsTeam1, penaltiesTeam1, team2, goalsTeam2, penaltiesTeam2");
            writer.println(toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    //save as window
    private void onExportCSV() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Match Report");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = chooser.showSaveDialog(null);

        if (file == null) {
            System.out.println("File not saved");
            return;
        }

        exportMatchToCSV(file);
    }
}
