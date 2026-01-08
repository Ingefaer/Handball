package com.example.presentation;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void switchToSelectTeam() throws IOException {
        App.setRoot("selectTeam");
    }
}
