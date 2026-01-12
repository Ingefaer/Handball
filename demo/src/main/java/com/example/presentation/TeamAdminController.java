package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamAdminController implements Initializable {

    Team chosenTeam;
    DataLayer data =  new DataLayer();

    @FXML
    public ListView<Team> teamsListView;
    @FXML
    public Button switchToMenuButton, createTeamButton, updateTeamButton, deleteTeamButton;
    @FXML
    public TextField createTeamTextField, updateTeamTextField;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    @FXML
    public void createTeamOnButtonPressed(ActionEvent actionEvent) {
        //System.out.println("CreateTeamButtonPressed");
        String teamName = createTeamTextField.getText();
        Team team =  new Team(teamName);
        DataLayer data = new DataLayer();
        data.insertTeam(team);
        updateListView();
        createTeamTextField.clear();

    }
    @FXML
    public void updateTeamOnButtonPressed(ActionEvent actionEvent) {
        String updateTeamName = updateTeamTextField.getText();
        chosenTeam.setTeamName (updateTeamName);
        data.updateTeam(chosenTeam);
        updateListView();
        updateTeamTextField.clear();
    }

    @FXML
    public void deleteTeamOnButtonPressed(ActionEvent actionEvent) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListView();

        //Udtrækker data fra det valgte element fra list
        teamsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println(chosenTeam = newValue);
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
