package com.example.presentation;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void switchToSelectTeam() throws IOException {
        App.setRoot("selectTeam");
    }

    @FXML
    private void switchToTeamAdmin() throws IOException {
        App.setRoot("teamAdmin");
    }

    @FXML
    private void switchToSelectReport() throws IOException {
        App.setRoot("selectReport");
    }

    @FXML
    private void switchToLeague() throws IOException {
        App.setRoot("league");
    }
}
