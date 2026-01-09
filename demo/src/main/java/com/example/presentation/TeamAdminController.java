package com.example.presentation;

import com.example.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TeamAdminController {

    public Button switchToMenuButton;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}
