package com.example;

import java.io.IOException;

import com.example.data.DataLayer;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //TestStoredProcedure();
        scene = new Scene(loadFXML("menu"));
        stage.setScene(scene);
        stage.show();
    }


    private static void TestStoredProcedure() {
        DataLayer db = new DataLayer();

        int n = (db.getAllCustomers()).size();

    System.out.println("Antal kunder: " + n);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();
    }

}