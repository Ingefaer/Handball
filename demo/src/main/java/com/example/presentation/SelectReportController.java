package com.example.presentation;

import com.example.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SelectReportController {

    public Button switchToMenuButton;
    public Button SelectedReportButton;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToReport() throws IOException {
        App.setRoot("report");
    }
}
