package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Match;
import com.example.entities.Team;
import com.example.entities.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    DataLayer data = new DataLayer();
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
         timestamps = data.getGoals(match.getMatchID(), match.getTeam1ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
            goalsTeam1 = oList.size();
        } else if (teamNumber == 2){
            timestamps = data.getGoals(match.getMatchID(), match.getTeam2ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
            goalsTeam2 = oList.size();
        } else {
            System.out.println("fejl");
        }

    }
    //TODO tjek om det kan fjernes

    private ArrayList<Timestamp> getPenaltiesByTeam(int matchID, int teamID) {
        return data.getPenalties(matchID, teamID);
    }

    private void updatePenaltyListView(ListView<Timestamp> list, int teamNumber) {
        ArrayList<Timestamp> timestamps;
        if (teamNumber == 1) {
            timestamps = getPenaltiesByTeam(match.getMatchID(), match.getTeam1ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
        } else if (teamNumber == 2){
            timestamps = getPenaltiesByTeam(match.getMatchID(), match.getTeam2ID());
            ObservableList<Timestamp> oList = FXCollections.observableArrayList(timestamps);
            list.setItems(oList);
        } else {
            System.out.println("fejl");
        }
    }

    public static void exportMatchesToCSV(File file, List<Match> matches) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("MatchID, team1, team2, goalsTeam1, goalsTeam2, penaltiesTeam1, penaltiesTeam2");

            for (Match m : matches) {
                writer.println(m.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExportCSV() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Match Report");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = chooser.showSaveDialog(null);
        if (file == null) return;

        List<Match> list = new ArrayList<>();
        list.add(match);

        exportMatchesToCSV(file, list);
    }
}
