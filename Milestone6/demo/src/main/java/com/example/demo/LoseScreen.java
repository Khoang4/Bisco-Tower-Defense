package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoseScreen extends Application {

    private PlayerInformation info = new PlayerInformation();
    private Grid gameMap = new Grid();

    protected BorderPane loseScreen(Stage primaryStage) {
        gameMap.resetTowerGrid();

        VBox paneforPlay = new VBox(20);
        Button play = new Button("Replay");
        Button quit = new Button("Quit");
        Text title = new Text(0, 0, "You lost. Play again?");
        title.setFont(new Font("Comic Sans MS", 50));
        HelloApplication newScene = new HelloApplication();

        HBox stats = new HBox(20);
        Text stat1 = new Text(0, 0, "Total Money Spent: " + info.getMoneySpent());
        Text stat2 = new Text(0, 0, "Total Enemies Killed: " + info.getEnemiesKilled());
        Text stat3 = new Text(0, 0, "Total Towers Bought: " + info.getTowersBought());
        stats.getChildren().addAll(stat1, stat2, stat3);
        stats.setAlignment(Pos.CENTER);

        info.setMoneySpent(0);
        info.setEnemiesKilled(0);
        info.setTowersBought(0);

        play.setOnMouseClicked(e -> {
            newScene.start(primaryStage);
        });

        quit.setOnMouseClicked(e -> {
            Platform.exit();
        });


        paneforPlay.getChildren().addAll(title, play, quit, stats);
        paneforPlay.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(paneforPlay);

        return pane;
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene welcomeScene = new Scene(loseScreen(primaryStage), 1600, 900);
        primaryStage.setTitle("Bisco Tower Defense");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }
}
