package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Boss extends Enemy {

    public Boss(int health, int x, int y,
                Stage primaryStage, BorderPane setUp, HBox tInfo, Duration delay) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.primaryStage = primaryStage;
        this.path = transition(setUp, tInfo, delay);
        this.moneyValue = 2000;
        this.tInfo = tInfo;
        this.score = 100;
        this.damage = 200;
    }

    public PathTransition transition(BorderPane setUp, HBox tInfo, Duration delay) {
        Path path = createPath();
        Shape rect1 = new Rectangle(40, 40, Color.CADETBLUE);
        this.c = Color.CADETBLUE;
        setUp.getChildren().addAll(rect1);
        PathTransition transition = new PathTransition();
        transition.setNode(rect1);
        transition.setDuration(Duration.seconds(30));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.setDelay(delay);
        transition.setOnFinished(t -> {
            damageMonument();
        });

        return transition;
    }
}
