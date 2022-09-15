package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameScreen extends Application {
    private int money;
    private boolean placeTower;
    private boolean upgradeTower;
    private int takenMoney;
    private ImageView newTowerImage;
    private Circle newUpgradeImage;
    private int towerPlaced;
    private ArrayList<WizardTower> wizards = new ArrayList<>();
    private PlayerInformation info = new PlayerInformation();
    protected StackPane gameScreen(Stage primaryStage) {
        ImageView backGround = new ImageView(new Image(
                "https://cdn.discordapp.com/attachments/937928877307744286/949901046707265546/bg_1920x1080.png"));
        backGround.setFitHeight(900);
        backGround.setFitWidth(1600);
        Grid gameMap = new Grid();
        int health = info.getHealth();

        Text tMoney = new Text(0, 0, "Money: " + info.getMoney());
        Text tHealth = new Text(0, 0, "Health: " + health);
        tMoney.setFont(new Font("Comic Sans MS", 20));
        tHealth.setFont(new Font("Comic Sans MS", 20));
        tMoney.setFill(Color.GHOSTWHITE);
        tHealth.setFill(Color.GHOSTWHITE);

        Button tTest = new Button("Tower");
        Button startGame = new Button("Start");
        HBox playerInfo = new HBox(20);
        playerInfo.getChildren().addAll(tMoney, tHealth, tTest, startGame);
        playerInfo.setAlignment(Pos.CENTER);
        BorderPane towerSetup = new BorderPane();
        BorderPane textLayout = new BorderPane();
        textLayout.setTop(playerInfo);

        HBox warrior = new HBox(10);
        Button warr = new Button("Warrior");
        Button warrUp = new Button("Upgrade Warrior");
        WarriorTower warrIconTower = new WarriorTower();
        int warrInt = warrIconTower.getCost();
        Text warrCost = new Text(0, 0, "Cost: " + warrInt);
        ImageView warrIcon = warrIconTower.getSprite();
        warrior.getChildren().addAll(warrIcon, warr, warrUp, warrCost);
        warrior.setAlignment(Pos.CENTER);

        HBox wizard = new HBox(10);
        Button wiz = new Button("Wizard");
        Button wizUp = new Button("Upgrade Wizard");
        WizardTower wizzTowerIcon = new WizardTower();
        int wizInt = wizzTowerIcon.getCost();
        Text wizCost = new Text(0, 0, "Cost: " + wizInt);
        ImageView wizIcon = wizzTowerIcon.getSprite();
        wizard.getChildren().addAll(wizIcon, wiz, wizUp, wizCost);
        wizard.setAlignment(Pos.CENTER);

        HBox archer = new HBox(10);
        Button arch = new Button("Archer");
        Button archUp = new Button("Upgrade Archer");
        ArcherTower archTowerIcon = new ArcherTower();
        int archInt = archTowerIcon.getCost();
        Text archCost = new Text(0, 0, "Cost: " + archInt);
        ImageView archIcon = archTowerIcon.getSprite();
        archer.getChildren().addAll(archIcon, arch, archUp, archCost);
        archer.setAlignment(Pos.CENTER);

        VBox towerMenu = new VBox(10);
        towerMenu.getChildren().addAll(warrior, wizard, archer);
        towerMenu.setAlignment(Pos.CENTER);
        towerMenu.setStyle("-fx-background-color: #FFFFFF;");
        towerMenu.setDisable(true);
        towerMenu.setVisible(false);
        textLayout.setCenter(towerMenu);
        tTest.setOnMouseClicked(e -> {
            if (!towerMenu.isVisible() && !placeTower) {
                towerMenu.setDisable(false);
                towerMenu.setVisible(true);

            } else {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);

            }
        });

        warr.setOnMouseClicked(e -> {
            this.purchaseTower(warrIconTower);
            this.towerPlaced = 1;
            if (placeTower) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        wiz.setOnMouseClicked(e -> {
            this.purchaseTower(wizzTowerIcon);
            this.towerPlaced = 2;
            if (placeTower) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        arch.setOnMouseClicked(e -> {
            this.purchaseTower(archTowerIcon);
            this.towerPlaced = 3;
            if (placeTower) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        warrUp.setOnMouseClicked(e -> {
            this.upgradeTower(warrIconTower);
            this.towerPlaced = 4;
            if (upgradeTower) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        wizUp.setOnMouseClicked(e -> {
            this.upgradeTower(wizzTowerIcon);
            this.towerPlaced = 5;
            if (upgradeTower) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        archUp.setOnMouseClicked(e -> {
            this.upgradeTower(archTowerIcon);
            this.towerPlaced = 6;
            if (upgradeTower) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        textLayout.setOnKeyPressed(escape -> {
            if (escape.getCode() == KeyCode.ESCAPE && (placeTower || upgradeTower) && !towerMenu.isVisible()) {
                info.setMoney(info.getMoney() + takenMoney);
                placeTower = false;
            }

            if (escape.getCode() == KeyCode.ESCAPE && towerMenu.isVisible()) {
                towerMenu.setDisable(true);
                towerMenu.setVisible(false);
            }
        });

        textLayout.setOnMousePressed(f -> {
            int x = (int) f.getX();
            int y = (int) f.getY();
            if (gameMap.getBuildable(x / 100, y / 100) && placeTower) {
                if (gameMap.addTower(x / 100, y / 100, towerPlaced)) {
                    tMoney.setText("Money: " + info.getMoney());
                    newTowerImage.setLayoutX(((int) (f.getX() / 100)) * 100 - 10);
                    newTowerImage.setLayoutY(((int) (f.getY() / 100)) * 100 - 10);
                    towerSetup.getChildren().addAll(newTowerImage);
                    placeTower = false;
                    info.setTowersBought(info.getTowersBought() + 1);
                    info.setMoneySpent(info.getMoneySpent() + takenMoney);
                    if (towerPlaced == 2) {
                        wizards.add(new WizardTower(x, y, towerSetup, playerInfo));
                    }
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Can't place a tower here!");
                    error.showAndWait();
                }
            } else if (gameMap.getBuildable(x / 100, y / 100) && upgradeTower) {
                if (gameMap.upgradeTower(x / 100, y / 100, towerPlaced)) {
                    tMoney.setText("Money: " + info.getMoney());
                    newUpgradeImage.setLayoutX(((int) (f.getX() / 100)) * 100 + 50);
                    newUpgradeImage.setLayoutY(((int) (f.getY() / 100)) * 100 + 50);
                    towerSetup.getChildren().addAll(newUpgradeImage);
                    info.setMoneySpent(info.getMoneySpent() + takenMoney);
                    upgradeTower = false;
                    if (towerPlaced == 5) {
                        for (int i = 0; i < wizards.size(); i++) {
                            WizardTower w = wizards.get(i);
                            int posX = w.getX();
                            int posY = w.getY();

                            if ((x / 100) == (posX / 100) && (y / 100) == (posY / 100)) {
                                w.setUpgraded(true);
                                break;
                            }
                        }
                    }
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR, "You cannot upgrade here!");
                    error.showAndWait();
                }
            }
        });
        startGame.setOnMouseClicked(e -> {
            roundStart(towerSetup, playerInfo, primaryStage);
            playerInfo.getChildren().remove(3);
        });
        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(backGround, towerSetup, textLayout);
        return mainPane;
    }

    private void purchaseTower(TowerClass purchasing) {
        newTowerImage = purchasing.buyTower();
        if (newTowerImage == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Not enough money to purchase.");
            error.showAndWait();
        } else {
            takenMoney = purchasing.getCost();
            placeTower = true;
        }
    }

    private void upgradeTower(TowerClass purchasing) {
        newTowerImage = purchasing.buyTower();
        if (newTowerImage == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Not enough money to purchase.");
            error.showAndWait();
        } else {
            takenMoney = purchasing.getCost();
            upgradeTower = true;
            Circle newUpgradeImage = new Circle(50, Color.YELLOW);
            newUpgradeImage.setOpacity(0.5);
            this.newUpgradeImage = newUpgradeImage;
        }
    }

    @Override

    public void start(Stage primaryStage) {
        info.setAlive(true);
        info.setStage(primaryStage);
        Scene mainScene = new Scene(gameScreen(primaryStage), 1600, 900);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public void roundStart(BorderPane setUp, HBox playerInfo, Stage primaryStage) {
        Zombie zomb1 = new Zombie(200, 0, 0, primaryStage, setUp, playerInfo, Duration.ZERO);
        Zombie zomb2 = new Zombie(200, 0, 0, primaryStage, setUp, playerInfo, Duration.seconds(2));
        Skeleton skel1 = new Skeleton(100, 0, 0,
                primaryStage, setUp, playerInfo, Duration.seconds(5));
        Brute brut1 = new Brute(1000, 0, 0,
                primaryStage, setUp, playerInfo, Duration.seconds(8));
        Boss boss1 = new Boss(4000, 0, 0, primaryStage, setUp, playerInfo, Duration.seconds(20));

        List<Enemy> enemyList = new ArrayList<>(Arrays.asList(zomb1, zomb2, skel1, brut1, boss1));
        RoundThread obj = new RoundThread((ArrayList<Enemy>)
                enemyList, (ArrayList<WizardTower>) wizards);
        info.setEnemiesLeft(enemyList.size());
        obj.start();

    }


}
