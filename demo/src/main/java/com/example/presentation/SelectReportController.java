package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class SelectReportController {
    private DataLayer data = new DataLayer();
    private Match chosenMatch;

    @FXML
    private Label errorLabel;
    @FXML
    private ListView chooseMatchListView;

    @FXML
    private void initialize() {
        updateListView(chooseMatchListView);
        chooseFromList(chooseMatchListView);
        errorLabel.setVisible(false);
    }

    //Metode til at opdatere listview
    private void updateListView(ListView<Match> listview) {
        ArrayList<Match> matches = data.getAllMatches();
        ObservableList<Match> oList = FXCollections.observableArrayList(matches);
        listview.setItems(oList);
    }

    private void chooseFromList(ListView<Match> listview) {
        //Udtrækker data fra det valgte element fra list
        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                chosenMatch = newValue;
            }
        });

        //LV = listView
        //CellFactory er formatering af ListView
        //-> Lambada - lv -> new ListCell<Team>() = formater cellerne i listview istedet for hele listview
        //ListCell er et UI komponent som ListView bruger til at vise hvert komponent
        listview.setCellFactory(lv -> new ListCell<Match>() {
                    @Override
                    protected void updateItem(Match match, boolean empty) {
                        super.updateItem(match, empty);

                        if (empty || match == null) {
                            setText(null);
                        } else {
                            setText(String.format("%-5s %-20s vs %-5s %-20s",
                                    match.getMatchID(),
                                    match.getTeamName(1),
                                    "",
                                    match.getTeamName(2)
                            ));
                        }
                    }
                }
        );}

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToReport() throws IOException {
        if (chosenMatch != null) {

            FXMLLoader loader = new FXMLLoader(App.class.getResource("report.fxml"));
            Parent root = loader.load();

            ReportController reportController = loader.getController();
            reportController.importSelectedMatch(chosenMatch);

            App.setRoot(root);
        } else {
            errorLabel.setText("Vælg venligst en kamp!");
            errorLabel.setVisible(true);

        }
    }
}
