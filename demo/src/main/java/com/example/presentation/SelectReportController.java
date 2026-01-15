package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Match;
import com.example.entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectReportController implements Initializable {
    public Button switchToMenuButton;
    public Button SelectedReportButton;
    public ListView chooseMatchListView;

    Match match;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListView(chooseMatchListView);
        chooseFromList(chooseMatchListView);

    }

    //Metode til at opdatere listview
    private void updateListView(ListView<Match> list) {
        match = new Match();
        ArrayList<Match> matches = match.getAllMatches();
        ObservableList<Match> oList = FXCollections.observableArrayList(matches);
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
        if (match != null) {

            FXMLLoader loader = new FXMLLoader(App.class.getResource("report.fxml"));
            Parent root = loader.load();

            ReportController reportController = loader.getController();
            reportController.importSelectedMatch(match);

            App.setRoot(root);
        } else {
            //ToDo HUSK OG FJERN DETTE
            System.out.println("Get fucked");
        }
    }
}
