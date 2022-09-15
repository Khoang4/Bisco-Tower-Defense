package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Enemy {

    protected int health;
    protected int x;
    protected int y;
    protected int damage;
    protected int moneyValue;
    protected int score;
    protected PathTransition path;
    protected Stage primaryStage;
    protected Color c;
    protected HBox tInfo;
    protected boolean stopped = false;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getScore() {
        return score;
    }

    public boolean testDamageMonument() {
        PlayerInformation info = new PlayerInformation();
        info.setHealth(info.getHealth() - this.damage);
        info.setScore(info.getScore() - score);
        if (info.getHealth() <= 0) {
            return false;
            // LoseScreen newScene = new LoseScreen();
            // newScene.start(primaryStage);
        }
        return true;
    }
    public void damageMonument() {
        PlayerInformation info = new PlayerInformation();
        info.checkDamage(this.damage, primaryStage, tInfo);
    }

    public Path createPath() {
        Path path = new Path();
        path.getElements().add(new MoveTo(150, -10));
        path.getElements().add(new VLineTo(450));
        path.getElements().add(new HLineTo(250));
        path.getElements().add(new VLineTo(650));
        path.getElements().add(new HLineTo(450));
        path.getElements().add(new VLineTo(50));
        path.getElements().add(new HLineTo(650));
        path.getElements().add(new VLineTo(830));
        path.getElements().add(new HLineTo(850));
        path.getElements().add(new VLineTo(450));
        path.getElements().add(new HLineTo(1650));

        return path;
    }

    public boolean damage(int damage) {
        this.health -= damage;
        ((Shape) path.getNode()).setFill(c);
        if (this.health <= 0) {
            Grid map = new Grid();
            path.stop();
            path.getNode().setVisible(false);
            PlayerInformation info = new PlayerInformation();
            info.setMoney(info.getMoney() + this.moneyValue);
            info.setScore(info.getScore() + this.score);
            ((Text) (this.tInfo.getChildren().get(0))).setText("Money: " + info.getMoney());
            if (info.isAlive()) {
                info.setEnemiesKilled(info.getEnemiesKilled() + 1);
            }
            return false;
        }
        return true;
    }

    public void testDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            PlayerInformation info = new PlayerInformation();
            info.setMoney(info.getMoney() + this.moneyValue);
            info.setScore(info.getScore() + this.score);
        }
    }

    public int getHealth() {
        return health;
    }

    public void defeat() {
        PlayerInformation info = new PlayerInformation();
        info.setScore(info.getScore() + score);
        info.setEnemiesKilled(info.getEnemiesKilled() + 1);
    }

    public void move() {
        path.play();
    }

    public void afterDamage() {
        ((Shape) (this.path.getNode())).setFill(Color.RED);
    }

    public void changeStopped() {
        if (this.stopped) {
            this.stopped = false;
            ((Shape) path.getNode()).setFill(c);
            this.path.play();
        } else {
            this.stopped = true;
            ((Shape) path.getNode()).setFill(Color.GREEN);
            this.path.pause();
        }
    }
    public boolean testChangeStopped() {
        return !this.stopped;
    }


}
