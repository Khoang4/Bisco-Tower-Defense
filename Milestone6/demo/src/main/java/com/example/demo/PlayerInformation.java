package com.example.demo;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerInformation {
    private static String name;
    private static String difficulty;
    private static int money;
    private static int health;
    private static int score;
    private static int wizardTotal;
    private Grid gameMap = new Grid();
    private static boolean alive = true;
    private static Stage primaryStage;
    private static int enemiesLeft;

    private static int moneySpent = 0;
    private static int enemiesKilled = 0;
    private static int towersBought = 0;

    public PlayerInformation() {

    }

    public PlayerInformation(String name, String difficulty) {
        score = 0;
        wizardTotal = 0;
        moneySpent = 0;
        enemiesKilled = 0;
        towersBought = 0;
        PlayerInformation.name = name;
        PlayerInformation.difficulty = difficulty;
        if (difficulty.equals("Easy")) {
            money = 1750;
            health = 200;
        } else if (difficulty.equals("Medium")) {
            money = 1250;
            health = 150;
        } else {
            money = 1000;
            health = 100;
        }
    }


    public void testCheckDamage(int damaged) {
        this.setHealth(this.getHealth() - damaged);
        if (this.getHealth() <= 0) {
            this.alive = false;
        }
    }
    public void checkDamage(int damaged, Stage primaryStage, HBox tInfo) {
        this.setHealth(this.getHealth() - damaged);
        this.setScore(this.getScore() - 25);
        if (this.getHealth() <= 0) {
            this.alive = false;
            LoseScreen newScene = new LoseScreen();
            newScene.start(primaryStage);
        }
        ((Text) (tInfo.getChildren().get(1))).setText("Health: " + this.getHealth());
    }

    public void winGame() {
        WinScreen newScene = new WinScreen();
        newScene.start(primaryStage);
    }

    public void updateWizardTotal(int gain) {
        wizardTotal += gain;
    }
    public int getWizardTotal() {
        return wizardTotal;
    }
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int mon) {
        money = mon;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }


    public int getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(int moneySpent) {
        this.moneySpent = moneySpent;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public int getTowersBought() {
        return towersBought;
    }

    public void setTowersBought(int towersBought) {
        this.towersBought = towersBought;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static int getEnemiesLeft() {
        return enemiesLeft;
    }

    public static void setEnemiesLeft(int enemiesLeft) {
        PlayerInformation.enemiesLeft = enemiesLeft;
    }
}
