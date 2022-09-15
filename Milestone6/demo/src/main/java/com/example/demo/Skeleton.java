package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Skeleton extends Enemy {
    public Skeleton() {
        this.health = 25;
        this.damage = 10;
        this.moneyValue = 30;
        this.score = 100;
        this.x = 0;
        this.y = 0;
    }
    public Skeleton(int health, int x, int y,
                    Stage primaryStage, BorderPane setUp, HBox tInfo, Duration delay) {
        this();
        this.health = health;
        this.x = x;
        this.y = y;
        this.primaryStage = primaryStage;
        this.path = transition(setUp, tInfo, delay);
        this.moneyValue = 30;
        this.tInfo = tInfo;
    }



    public PathTransition transition(BorderPane setUp, HBox tInfo, Duration delay) {
        Path path = createPath();
        Shape circle1 = new Circle(20, Color.BLUEVIOLET);
        this.c = Color.BLUEVIOLET;
        setUp.getChildren().addAll(circle1);
        PathTransition transition = new PathTransition();
        transition.setNode(circle1);
        transition.setDuration(Duration.seconds(8));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.setDelay(delay);
        transition.setOnFinished(t -> {
            damageMonument();
        });

        return transition;
    }
}
