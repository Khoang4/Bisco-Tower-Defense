package com.example.demo;


public class ArcherTower extends TowerClass {
    private final PlayerInformation info = new PlayerInformation();
    public ArcherTower() {
        spriteIcon = "https://cdn.discordapp.com/attachments/937928877307744286/950600027695366205/Archer_Icon_1.png";
        sprite = "https://cdn.discordapp.com/attachments/937928877307744286/950983589351280660/Archer_Tower.png";
        this.stunTime = 150;
        this.type = 2;
        String difficulty = info.getDifficulty();
        if (difficulty.equals("Easy")) {
            cost = 500;
        } else if (difficulty.equals("Medium")) {
            cost = 625;
        } else {
            cost = 750;
        }
        damage = 20;
    }

    public ArcherTower(int x, int y) {
        this();
        this.x = x;
        this.y = y;
        this.type = 2;
    }


    public int getCost() {
        return cost;
    }

    public int getStunTime() {
        return stunTime;
    }

    public int[][] affects() {
        return new int[0][];
    }
}
