package com.example.demo;




public class WarriorTower extends TowerClass {

    private final PlayerInformation info = new PlayerInformation();


    public WarriorTower() {
        sprite = "https://cdn.discordapp.com/attachments/937928877307744286/950983589531627540/Warrior_Tower.png";
        spriteIcon = "https://cdn.discordapp.com/attachments/937928877307744286/950600027087204432/Warrior_Icon_1.png";
        String difficulty = info.getDifficulty();
        type = 1;
        if (difficulty.equals("Easy")) {
            cost = 250;
        } else if (difficulty.equals("Medium")) {
            cost = 375;
        } else {
            cost = 500;
        }
        damage = 150;
    }
    public WarriorTower(int x, int y) {
        this();
        this.x = x;
        this.y = y;
        this.type = 1;

    }

    public int getCost() {
        return cost;
    }





}
