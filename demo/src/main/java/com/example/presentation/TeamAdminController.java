package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;

public class TeamAdminController {
    private Team chosenTeam;
    private DataLayer data =  new DataLayer();

    @FXML
    private ListView<Team> teamsListView;
    @FXML
    private TextField createTeamTextField, updateTeamTextField;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    @FXML
    private void createTeamOnButtonPressed() {
        String teamName = createTeamTextField.getText();
        Team team =  new Team(teamName);
        data.insertTeam(team);
        updateListView();
        createTeamTextField.clear();

    }
    @FXML
    private void updateTeamOnButtonPressed() {
        String updateTeamName = updateTeamTextField.getText();
        chosenTeam.setTeamName (updateTeamName);
        data.updateTeam(chosenTeam);
        updateListView();
        updateTeamTextField.clear();
    }

    @FXML
    private void deleteTeamOnButtonPressed() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Vil du slette det valgte hold: " + chosenTeam.getTeamName());
        alert.setHeaderText("");
        if (alert.showAndWait().get() == ButtonType.OK) {
            data.deleteTeam(chosenTeam);
        }
        //der er ikke behov for en "else", fordi vi ønsker intet at gøre hvis der trykkes cancel
        updateListView();
    }

    @FXML
    private void initialize() {
        //Listviewet opdateres her, så der faktisk er et listview når vi kommer ind på siden.
        updateListView();
        // ENTER til opret
        createTeamTextField.setOnAction(e -> createTeamOnButtonPressed());

        // ENTER til opdater
        updateTeamTextField.setOnAction(e -> updateTeamOnButtonPressed());


        //Udtrækker data fra det valgte element fra list
        teamsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                chosenTeam = newValue;
            }
        });

        //LV = listView
        //CellFactory er formatering af ListView
        //-> Lambada - lv -> new ListCell<Team>() = formater cellerne i listview istedet for hele listview
        //ListCell er et UI komponent som ListView bruger til at vise hvert komponent
        teamsListView.setCellFactory(lv -> new ListCell<Team>() {
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

    //Metode til at opdatere listview
    private void updateListView() {
        ArrayList<Team> teams = data.getAllTeams();
        ObservableList<Team> oList = FXCollections.observableArrayList(teams);
        teamsListView.setItems(oList);
    }
}
