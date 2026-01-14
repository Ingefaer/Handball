package com.example.presentation;

import com.example.App;
import com.example.data.DataLayer;
import com.example.entities.Team;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeagueController implements Initializable {
    @FXML
    public TableView<Team> leagueTableView;

    @FXML
    private TableColumn<Team, String> teamName;

    @FXML
    private TableColumn<Team, Integer> points;

    @FXML
    private TableColumn<Team, Integer> rank;

    @FXML
    private Button switchToMenuButton;
    DataLayer data = new DataLayer();

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
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
