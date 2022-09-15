package com.example.demo;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class TowerClass {
    protected int type; // [1: warrior, 2: archer, 3: mage] <- maybe make this an enum?
    protected int cost;

    protected String sprite; // Image file to be used
    protected String spriteIcon;
    protected PathTransition path;

    protected int damage;
    protected int stunTime;
    protected int moneyGrant;
    protected int x; //coordinates
    protected int y;





    public abstract int getCost();

    public ImageView buyTower() {
        PlayerInformation info = new PlayerInformation();
        Image spriteTower = new Image(sprite);
        int currMoney = info.getMoney();
        if (currMoney >= cost) {
            info.setMoney(currMoney - cost);
            return new ImageView(spriteTower);
        } else {
            return null;
        }
    }
    public boolean testBuyTower() {
        PlayerInformation info = new PlayerInformation();
        int currMoney = info.getMoney();
        if (currMoney >= cost) {
            info.setMoney(currMoney - cost);
            info.setMoneySpent(info.getMoneySpent() + cost);
            info.setTowersBought(info.getTowersBought() + 1);
            return true;
        } else {
            return false;
        }
    }
    public ImageView getSprite() {
        Image iconSprite = new Image(spriteIcon);
        return new ImageView(iconSprite);
    }

    public boolean testUpgradeTower() {
        PlayerInformation info = new PlayerInformation();
        if (info.getMoney() >= cost) {
            if (this.getType() == 1) {
                this.damage = 250;
            }
            if (this.getType() == 2) {
                this.stunTime = 250;
            }
            if (this.getType() == 3) {
                this.moneyGrant = 90;
            }
            this.type += 3;
            info.setMoney(info.getMoney() - cost);
            info.setMoneySpent(info.getMoneySpent() + cost);
            return true;
        }
        return false;
    }

    public int getDamage() {
        return damage;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
