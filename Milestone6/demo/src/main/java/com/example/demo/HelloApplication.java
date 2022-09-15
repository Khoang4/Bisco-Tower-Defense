package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {


    protected BorderPane welcomeScreen(Stage primaryStage) {
        VBox paneforPlay = new VBox(20);
        Button play = new Button("Play");
        Button quit = new Button("Quit");
        Text title = new Text(0, 0, "Bisco Tower Defense");
        title.setFont(new Font("Comic Sans MS", 50));


        paneforPlay.getChildren().addAll(title, play, quit);
        paneforPlay.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(paneforPlay);
        ConfigScreen newScene = new ConfigScreen();
        play.setOnMouseClicked(e -> {
            try {
                newScene.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        quit.setOnMouseClicked(e -> {
            Platform.exit();
        });

        return pane;
    }





    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene welcomeScene = new Scene(welcomeScreen(primaryStage), 1600, 900);
        primaryStage.setTitle("Bisco Tower Defense");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     * @param args the main arguments put into the main starting program
     */
    public static void main(String[] args) {
        launch(args);
    }
}