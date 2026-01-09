package com.example.presentation;

import com.example.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ReportController {
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToSelectReport() throws IOException {
        App.setRoot("selectReport");
    }
}
