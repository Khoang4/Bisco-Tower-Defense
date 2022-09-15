package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class WizardTower extends TowerClass     {
    private int x;
    private int y;
    private PathTransition path;
    private final PlayerInformation info = new PlayerInformation();
    private int counter = 0;
    private HBox tInfo;
    private boolean upgraded = false;

    public WizardTower() {
        sprite = "https://cdn.discordapp.com/attachments/937928877307744286/950983589175107594/Wizard_Tower.png";
        spriteIcon = "https://cdn.discordapp.com/attachments/937928877307744286/950600027397554196/Wizard_Icon_1.png";
        String difficulty = info.getDifficulty();
        this.moneyGrant = 30;
        this.type = 3;
        if (difficulty.equals("Easy")) {
            this.cost = 750;
        } else if (difficulty.equals("Medium")) {
            this.cost = 875;
        } else {
            this.cost = 1000;
        }
    }

    public WizardTower(int x, int y, BorderPane setUp, HBox tInfo) {
        this();
        this.x = x;
        this.y = y;
        this.type = 3;
        this.tInfo = tInfo;
        this.path = generateCircle(setUp);
    }

    public int getMoneyGrant() {
        return moneyGrant;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void increaseMoney() {
        PlayerInformation info = new PlayerInformation();
        info.setMoney(info.getMoney() + moneyGrant);
        info.updateWizardTotal(10);

    }
    public int getCost() {
        return cost;
    }


    public PathTransition generateCircle(BorderPane setUp) {
        Circle notSun = new Circle(10, Color.YELLOW);
        setUp.getChildren().addAll(notSun);
        Path path = new Path();
        path.getElements().add(new MoveTo(this.x, this.y));
        path.getElements().add(new VLineTo(this.y - 50));

        PathTransition transition = new PathTransition();
        transition.setNode(notSun);
        transition.setDuration(Duration.seconds(1));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.setOnFinished(t -> {
            notSun.setVisible(false);
        });

        return transition;
    }

    public void moveCircle() {
        if (this.counter == 3) {
            ((Circle) this.path.getNode()).setVisible(true);
            this.counter = 0;
            generateMoney();
            this.path.play();
        } else {
            this.counter++;
        }

    }

    public void generateMoney() {
        int money = 30;
        if (this.upgraded) {
            money = 90;
        }
        info.setMoney(info.getMoney() + money);
        info.updateWizardTotal(money);
        ((Text) (this.tInfo.getChildren().get(0))).setText("Money: " + info.getMoney());
    }

    public void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }
}


