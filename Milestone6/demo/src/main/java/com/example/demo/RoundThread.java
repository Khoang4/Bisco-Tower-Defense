package com.example.demo;

import javafx.animation.PathTransition;


import java.util.Iterator;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import java.util.ArrayList;

public class RoundThread extends Thread {

    private List<Enemy> enemyList;
    private List<WizardTower> wizards;
    private Grid gameMap = new Grid();
    private PlayerInformation info = new PlayerInformation();

    public RoundThread(ArrayList<Enemy> enemyList, ArrayList<WizardTower> wizards) {
        this.enemyList = enemyList;
        this.wizards = wizards;
    }

    public void run() {

        for (Enemy cEnemy : enemyList) {
            cEnemy.move();
        }

        while (enemyList.size() > 0 && info.isAlive()) {
            try {
                Thread.sleep(1200);
            } catch (Exception e) {

            }

            for (WizardTower w : wizards) {
                w.moveCircle();
            }

            Iterator<Enemy> enemies = enemyList.iterator();
            while (enemies.hasNext()) {
                Enemy currEnemy = enemies.next();
                PathTransition currPath = currEnemy.path;
                Bounds boundBox =
                        currPath.getNode().localToScene(currPath.getNode().getBoundsInLocal());
                int posX = (int) boundBox.getMinX() / 100;
                int posY = (int) boundBox.getMinY() / 100;
                boolean broke = false;
                for (int i = posX - 2; i <= posX + 2; i++) {
                    if (broke) {
                        broke = false;
                        break;
                    }
                    for (int j = posY - 2; j <= posY + 2; j++) {
                        if (boundBox.getMaxY() > 50 && gameMap.getTower(i, j) == 1) {
                            currEnemy.afterDamage();
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {

                            }
                            boolean alive = currEnemy.damage(150);
                            if (!alive) {
                                enemies.remove();
                                broke = true;
                                break;
                            }
                        }
                        if (boundBox.getMaxY() > 50 && gameMap.getTower(i, j) == 4) {
                            currEnemy.afterDamage();
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {

                            }
                            boolean alive = currEnemy.damage(250);
                            if (!alive) {
                                enemies.remove();
                                broke = true;
                                break;
                            }
                        }
                        if (gameMap.getTower(i, j) == 3) {
                            currEnemy.changeStopped();
                            try {
                                Thread.sleep(150);
                            } catch (Exception e) {

                            }
                            currEnemy.changeStopped();
                        }
                        if (gameMap.getTower(i, j) == 6) {
                            currEnemy.changeStopped();
                            try {
                                Thread.sleep(250);
                            } catch (Exception e) {

                            }
                            currEnemy.changeStopped();
                        }
                    }
                }
            }
        }
        if (info.isAlive()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    info.winGame();
                }
            });
        } else {
            Iterator<Enemy> aliveEnemies = enemyList.iterator();
            while (aliveEnemies.hasNext()) {
                Enemy currEnemy = aliveEnemies.next();
                boolean alive = currEnemy.damage(10000);
                aliveEnemies.remove();
            }
        }
    }
}
