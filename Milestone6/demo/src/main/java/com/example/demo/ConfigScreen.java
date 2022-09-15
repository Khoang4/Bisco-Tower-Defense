package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConfigScreen.class.getResource("Config.fxml"));
        Scene config = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("Bisco Tower Defense");
        stage.setScene(config);
        stage.show();
    }

}
