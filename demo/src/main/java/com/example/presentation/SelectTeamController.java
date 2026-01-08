package com.example.presentation;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;

public class SelectTeamController {

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}