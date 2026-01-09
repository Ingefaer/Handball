package com.example.presentation;

import com.example.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class MatchController {
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}
