package com.example.demo;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Milestone5Tests {
    private PlayerInformation info;
    @BeforeEach
    void createPlayer() {
        info = new PlayerInformation("xd", "Easy");
    }

    @Test
    @DisplayName("Enemies have the proper money values allocated to them")
    void testMoneyValues() {
        Zombie zomb = new Zombie();
        Skeleton skell = new Skeleton();
        Brute brutis = new Brute();
        assertEquals(25, zomb.moneyValue);
        assertEquals(30, skell.moneyValue);
        assertEquals(100, brutis.moneyValue);
    }

    @Test
    @DisplayName("Ensures that the enemy initialized correctly onto the start of the path")
    void testEnemyPosition() {
        Zombie zomb = new Zombie();
        assertEquals(0, zomb.getX());
        assertEquals(0, zomb.getY());
    }

    @Test
    @DisplayName("Enemies give money to player once defeated")
    void testMoneyDrop() {
        Zombie zomb = new Zombie();
        zomb.testDamage(1000);
        assertEquals(1775, info.getMoney());
    }

    @Test
    @DisplayName("Enemies take proper damage value")
    void testTakingDamage() {
        WarriorTower warr = new WarriorTower();
        Zombie zomb = new Zombie();
        zomb.testDamage(warr.getDamage());
        assertEquals(50, zomb.getHealth());
    }

    @Test
    @DisplayName("Warrior tower has a damage value assigned to it")
    void testTowerDamageValue() {
        WarriorTower warr = new WarriorTower();
        assertEquals(150, warr.getDamage());
    }

    @Test
    @DisplayName("Archer towers have a stun that runs for a certain amount of time")
    void testArchStun() {
        ArcherTower arch = new ArcherTower();
        assertEquals(150, arch.getStunTime());
    }

    @Test
    @DisplayName("wizardTower have a value for granting money")
    void testWizzFarmValue() {
        WizardTower wizz = new WizardTower();
        assertEquals(30, wizz.getMoneyGrant());
    }

    @Test
    @DisplayName("wizardTower properly updates the player when summoning money to them")
    void testWizzFarmGive() {
        WizardTower wizz = new WizardTower();
        wizz.increaseMoney();
        assertEquals(1780, info.getMoney());
    }

    @Test
    @DisplayName("Check to see if the wizard tower updates the total amount gained score")
    void testWizzScore() {
        WizardTower wizz = new WizardTower();
        wizz.increaseMoney();
        wizz.increaseMoney();
        wizz.increaseMoney();
        wizz.increaseMoney();
        assertEquals(40, info.getWizardTotal());
    }

    @Test
    @DisplayName("checks to see the enemy propery spots when attacked by the archer class")
    void testStoppingEnemy() {
        Zombie zomb = new Zombie();
        assertTrue(zomb.testChangeStopped());
    }
}