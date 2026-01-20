package com.example.presentation;

import java.io.IOException;
import java.util.ArrayList;

import com.example.App;

import com.example.data.DataLayer;
import com.example.entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class SelectTeamController {

    private DataLayer data =  new DataLayer();
    private Team team1,team2;
    @FXML
    private ListView team1ListView, team2ListView;
    @FXML
    public Label errorLabel;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToMatch() throws IOException {
        if (team1 != null && team2 != null && team1.getTeamID()!=team2.getTeamID()) {

            FXMLLoader loader = new FXMLLoader(App.class.getResource("match.fxml"));
            Parent root = loader.load();

            MatchController matchController = loader.getController();
            matchController.setTeams(team1, team2);

            App.setRoot(root);
        } else {
            errorLabel.setText("VÆLG TO FORSKELLIGE HOLD!");
        }
    }

    @FXML
    private void initialize() {
        updateListView(team1ListView);
        updateListView(team2ListView);
        chooseFromList(team1ListView);
        chooseFromList(team2ListView);
    }

    //Metode til at opdatere listview
    private void updateListView(ListView<Team> listView) {
        ArrayList<Team> teams = data.getAllTeams();
        ObservableList<Team> oList = FXCollections.observableArrayList(teams);
        listView.setItems(oList);
    }

    private void chooseFromList(ListView<Team> listview) {
    //Udtrækker data fra det valgte element fra list
        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            if (listview.equals(team1ListView)) {
                team1 = newValue;
            } else if (listview.equals(team2ListView)) {
                team2 = newValue;
            }

        }
    });

    //LV = listView
    //CellFactory er formatering af ListView
    //-> Lambada - lv -> new ListCell<Team>() = formater cellerne i listview istedet for hele listview
    //ListCell er et UI komponent som ListView bruger til at vise hvert komponent
        listview.setCellFactory(lv -> new ListCell<Team>() {
        @Override
        protected void updateItem(Team team, boolean empty) {
            super.updateItem(team, empty);

            if (empty || team == null) {
                setText(null);
            } else {
                setText(team.getTeamName());
            }
        }
    }
        );}

}