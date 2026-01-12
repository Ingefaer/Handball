package com.example.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.App;

import com.example.data.DataLayer;
import com.example.entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class SelectTeamController implements Initializable {

    DataLayer data =  new DataLayer();
    Team team1,team2;
    @FXML
    public Button switchToMenuButton, switchToMatchButton;
    @FXML
    public ListView team1ListView, team2ListView;
    @FXML
    public Label errorLabel;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToMatch() throws IOException {
        if (team1 != null && team2 != null && team1.getTeamID()!=team2.getTeamID()) {
            App.setRoot("match");
        } else {
            errorLabel.setText("VÆLG TO FORSKELLIGE HOLD!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListView(team1ListView);
        updateListView(team2ListView);
        chooseFromList(team1ListView);
        chooseFromList(team2ListView);
    }

    //Metode til at opdatere listview
    private void updateListView(ListView<Team> list) {
        ArrayList<Team> teams = data.getAllTeams();
        ObservableList<Team> oList = FXCollections.observableArrayList(teams);
        list.setItems(oList);
    }

    private void chooseFromList(ListView<Team> list) {
    //Udtrækker data fra det valgte element fra list
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            if (list.equals(team1ListView)) {
                System.out.println(team1 = newValue);
                team1 = newValue;
            } else if (list.equals(team2ListView)) {
                System.out.println(team2 = newValue);
                team2 = newValue;
            }

        }
    });

    //LV = listView
    //CellFactory er formatering af ListView
    //-> Lambada - lv -> new ListCell<Team>() = formater cellerne i listview istedet for hele listview
    //ListCell er et UI komponent som ListView bruger til at vise hvert komponent
        list.setCellFactory(lv -> new ListCell<Team>() {
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