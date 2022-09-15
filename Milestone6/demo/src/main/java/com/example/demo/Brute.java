package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Brute extends Enemy {
    public Brute() {
        this.health = 50;
        this.damage = 200;
        this.moneyValue = 100;
        this.score = 200;
        this.x = 0;
        this.y = 0;
    }
    public Brute(int health, int x, int y,
                 Stage primaryStage, BorderPane setUp, HBox tInfo, Duration delay) {
        this();
        this.health = health;
        this.x = x;
        this.y = y;
        this.primaryStage = primaryStage;
        this.path = transition(setUp, tInfo, delay);
        this.moneyValue = 100;
        this.tInfo = tInfo;
    }


    public PathTransition transition(BorderPane setUp, HBox tInfo, Duration delay) {
        Path path = createPath();
        Shape circle1 = new Circle(20, Color.DARKBLUE);
        this.c = Color.DARKBLUE;
        setUp.getChildren().addAll(circle1);
        PathTransition transition = new PathTransition();
        transition.setNode(circle1);
        transition.setDuration(Duration.seconds(20));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.setDelay(delay);
        transition.setOnFinished(t -> {
            damageMonument();
        });

        return transition;
    }
}
