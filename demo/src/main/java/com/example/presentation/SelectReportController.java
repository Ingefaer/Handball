package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Match;
import com.example.entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectReportController {
    DataLayer data =  new DataLayer();
    public Button switchToMenuButton;
    public Button SelectedReportButton;
    public ListView chooseMatchListView;

    Match match;
    /*public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListView(chooseMatchListView);
        chooseFromList(chooseMatchListView);

    }

    //Metode til at opdatere listview
    private void updateListView(ListView<Match> list) {
        ArrayList<Match> match = data.getAllMatches();
        ObservableList<Team> oList = FXCollections.observableArrayList(match);
        list.setItems(oList);
    }

    private void chooseFromList(ListView<Match> list) {
        //Udtrækker data fra det valgte element fra list
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                match = newValue;
                System.out.println(match);
            }
        });

        //LV = listView
        //CellFactory er formatering af ListView
        //-> Lambada - lv -> new ListCell<Team>() = formater cellerne i listview istedet for hele listview
        //ListCell er et UI komponent som ListView bruger til at vise hvert komponent
        list.setCellFactory(lv -> new ListCell<Match>() {
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

    **/
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToReport() throws IOException {
        App.setRoot("report");
    }
}
