package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    //bruges til første metode
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Håndbold app");
        Image icon = new Image("/ico.png");
        stage.getIcons().add(icon);
        scene = new Scene(loadFXML("menu"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    //sceneskift når der ikke skal information med
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //sceneskift når der ikke skal information med
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //overloading til når der skal information med videre fra et sceneskift - bruger ikke fxml loader
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        launch();
    }
}