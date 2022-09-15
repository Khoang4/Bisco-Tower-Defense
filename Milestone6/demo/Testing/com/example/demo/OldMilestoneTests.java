package com.example.demo;


import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OldMilestoneTests {
    private Grid gameMap;

    @BeforeEach
    void createMap() {
        gameMap = new Grid();
    }

    @Test
    @DisplayName("An area should not be buildable if it is a path")
    void testBuildable() {

        assertFalse(gameMap.getBuildable(0, 0)); // empty ti
        assertFalse(gameMap.getBuildable(1, 0)); // path tile
        assertTrue(gameMap.getBuildable(2, 0)); // buildable!

    }

    @Test
    @DisplayName("An area should not be buildable if an object is already present")
    void testExistingTower() {
        gameMap.addTower(2, 0, 1);
        assertFalse(gameMap.addTower(2, 0, 1));
    }

    @Test
    @DisplayName("Placing a tower should occupy a spot")
    void testOccupySpot() {
        gameMap.addTower(2, 0, 1);
        assertTrue(gameMap.getBuildable(2, 0));
    }

    @Test
    @DisplayName("Selecting different difficulties should result in differing amounts of money")
    void testDifferentMoney() {
        PlayerInformation player1 = new PlayerInformation("Tester1", "Easy");
        int money1 = player1.getMoney();
        PlayerInformation player2 = new PlayerInformation("Tester2", "Medium");
        int money2 = player2.getMoney();
        PlayerInformation player3 = new PlayerInformation("Tester3", "Hard");
        int money3 = player3.getMoney();

        assertNotEquals(money1, money2);
        assertNotEquals(money1, money3);
        assertNotEquals(money2, money3);
    }

    @Test
    @DisplayName("Towers should cost differently based on difficulty.")
    void testTowerCosts() {
        PlayerInformation player1 = new PlayerInformation("Tester1", "Easy");
        WarriorTower warr1 = new WarriorTower(1, 1);
        PlayerInformation player2 = new PlayerInformation("Tester2", "Medium");
        WarriorTower warr2 = new WarriorTower(1, 1);
        PlayerInformation player3 = new PlayerInformation("Tester3", "Hard");
        WarriorTower warr3 = new WarriorTower(1, 1);

        assertFalse(warr1.getCost() == warr2.getCost());
        assertFalse(warr1.getCost() == warr3.getCost());
        assertFalse(warr2.getCost() == warr3.getCost());
    }

    @Test
    @DisplayName("Player should not be able to buy tower if they lack funds.")
    void testInsufficientFunds() {
        PlayerInformation player1 = new PlayerInformation("Tester1", "Easy");
        WarriorTower warr1 = new WarriorTower(1, 1);
        assertTrue(warr1.testBuyTower());
        player1.setMoney(0);
        assertFalse(warr1.testBuyTower());
    }

    @Test
    @DisplayName("Buying a tower should remove the correct amount of money.")
    void testSpentMoney() {
        PlayerInformation player1 = new PlayerInformation("Tester1", "Easy");
        WarriorTower warr1 = new WarriorTower(1, 1);
        warr1.testBuyTower();
        assertEquals(1500, player1.getMoney());
        warr1.testBuyTower();
        assertEquals(1250, player1.getMoney());
        player1.setMoney(250);
        warr1.testBuyTower();
        assertEquals(0, player1.getMoney());
    }

    @Test
    @DisplayName("Tests if name is valid")
    void testName() {
        ConfigController menuScreen = new ConfigController();
        assertFalse(menuScreen.testNameAndDiff("", "Easy"));
        assertFalse(menuScreen.testNameAndDiff("  ", "Easy"));
        assertTrue(menuScreen.testNameAndDiff("Who's Gonna Look Here?", "Easy"));
    }

    @Test
    @DisplayName("Tests if difficulty is valid")
    void testDifficulty() {
        ConfigController menuScreen = new ConfigController();
        assertFalse(menuScreen.testNameAndDiff("Never gonna", ""));
        assertTrue(menuScreen.testNameAndDiff("Give", "Easy"));
        assertTrue(menuScreen.testNameAndDiff("You", "Medium"));
        assertTrue(menuScreen.testNameAndDiff("Up", "Hard"));

    }

    @Test
    @DisplayName("Tests is health changes based on difficulty")
    void testHealth() {
        PlayerInformation player1 = new PlayerInformation("I don't like sand.", "Easy");
        int health1 = player1.getHealth();
        PlayerInformation player2 = new PlayerInformation(
                "It's coarse and rough and irritating", "Medium");
        int health2 = player2.getHealth();
        PlayerInformation player3 = new PlayerInformation("and it gets everywhere", "Hard");
        int health3 = player3.getHealth();

        assertNotEquals(health1, health2);
        assertNotEquals(health1, health3);
        assertNotEquals(health2, health3);
    }

    //Milestone 4 Tests
    @Test
    @DisplayName("Tests that enemies have proper damage values")
    void testDamageValue() {
        Zombie zomb = new Zombie();
        Skeleton skell = new Skeleton();
        Brute brutis = new Brute();
        assertEquals(100, zomb.damage);
        assertEquals(10, skell.damage);
        assertEquals(200, brutis.damage);
    }

    @Test
    @DisplayName("Tests that the monument properly loses health when damaged by an enemy")
    void testMonumentHealth() {
        Zombie zomb = new Zombie();
        PlayerInformation player = new PlayerInformation("its a me", "Easy");
        int previousHealth = player.getHealth();
        zomb.testDamageMonument();
        assertNotEquals(previousHealth, player.getHealth());
    }

    @Test
    @DisplayName("Enemies have the proper health values")
    void testEnemyHealth() {
        Zombie zomb = new Zombie();
        Skeleton skell = new Skeleton();
        Brute brutis = new Brute();
        assertEquals(200, zomb.health);
        assertEquals(25, skell.health);
        assertEquals(50, brutis.health);
    }

    @Test
    @DisplayName("Enemies have the proper score value")
    void testEnemyScore() {
        Zombie zomb = new Zombie();
        Skeleton skell = new Skeleton();
        Brute brutis = new Brute();
        assertEquals(75, zomb.score);
        assertEquals(100, skell.score);
        assertEquals(200, brutis.score);
    }

    @Test
    @DisplayName("Enemies update the player's score when defeated")
    void testEnemyAddScore() {
        Zombie zomb = new Zombie();
        PlayerInformation info = new PlayerInformation("yeehaw", "Easy");
        zomb.defeat();
        assertEquals(75, info.getScore());
    }

    @Test
    @DisplayName("Enemies take away score when they hit the monument")
    void testEnemyRemoveScore() {
        Zombie zomb = new Zombie();
        PlayerInformation info = new PlayerInformation("chuhngus", "Easy");
        zomb.testDamageMonument();
        assertEquals(-75, info.getScore());
    }

    @Test
    @DisplayName("Game is Lost when monument reaches 0 or less health")
    void testLoseGame() {
        Zombie zomb = new Zombie();
        zomb.testDamageMonument();
        assertEquals(false, zomb.testDamageMonument());
    }

    @Test
    @DisplayName("Test If Enemies Are In Right Places.")
    void testPaths() {
        Zombie zomb = new Zombie();
        Skeleton skel = new Skeleton();
        Brute brut = new Brute();
        Path zombPath = zomb.createPath();
        Path skelPath = skel.createPath();
        Path brutPath = brut.createPath();

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


        String pathStr = path.toString();
        String zombStr = zombPath.toString();
        String skelStr = skelPath.toString();
        String brutStr = brutPath.toString();

        assertTrue(zombStr.equals(pathStr));
        assertTrue(skelStr.equals(pathStr));
        assertTrue(brutStr.equals(pathStr));
    }
    //milestone 5 tests

}