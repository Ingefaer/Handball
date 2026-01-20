package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Team;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.ArrayList;

public class LeagueController  {
    private DataLayer data = new DataLayer();
    @FXML
    private TableView<Team> leagueTableView;
    @FXML
    private TableColumn<Team, String> teamName;
    @FXML
    private TableColumn<Team, Integer> rank, points;


    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

        //behøver faktisk ikke at implemente initializable længere
        //se: https://docs.oracle.com/javase/10/docs/api/javafx/fxml/Initializable.html?utm_source=chatgpt.com
    @FXML
    private void initialize() {
        ArrayList<Team> teams = data.getLeague();
        ObservableList<Team> oList = FXCollections.observableArrayList(teams);
        leagueTableView.setItems(oList);

        teamName.setCellValueFactory( cell ->
                new SimpleStringProperty(cell.getValue().getTeamName())
        );

        points.setCellValueFactory(
                cell ->
                        new SimpleIntegerProperty(cell.getValue().getTeamPoint()).asObject()
        );

        rank.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(
                        leagueTableView.getItems().indexOf(cellData.getValue()) + 1
                )
        );
    }
}
