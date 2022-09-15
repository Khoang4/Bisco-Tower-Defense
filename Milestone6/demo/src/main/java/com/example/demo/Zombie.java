package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;


public class Zombie extends Enemy {

    public Zombie() {
        this.health = 200;
        this.damage = 100;
        this.moneyValue = 25;
        this.score = 75;
        this.x = 0;
        this.y = 0;
    }

    public Zombie(int health, int x, int y,
                  Stage primaryStage, BorderPane setUp, HBox tInfo, Duration delay) {
        this();
        this.health = health;
        this.x = x;
        this.y = y;
        this.primaryStage = primaryStage;
        this.path = transition(setUp, tInfo, delay);
        this.moneyValue = 25;
        this.tInfo = tInfo;
    }


    public PathTransition transition(BorderPane setUp, HBox tInfo, Duration delay) {
        Path path = createPath();
        Shape circle1 = new Circle(20, Color.LIGHTBLUE);
        this.c = Color.LIGHTBLUE;
        setUp.getChildren().addAll(circle1);
        PathTransition transition = new PathTransition();
        transition.setNode(circle1);
        transition.setDuration(Duration.seconds(15));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.setDelay(delay);
        transition.setOnFinished(t -> {
            damageMonument();
        });

        return transition;
    }


}
