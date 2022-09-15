package com.example.demo;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Milestone6Tests {
    private PlayerInformation info;
    @BeforeEach
    void createPlayer() {
        info = new PlayerInformation("xd", "Easy");
    }

    @Test
    @DisplayName("Upgrading the tower should be conducted if the player has enough money")
    void testUpgrades() {
        WarriorTower warr = new WarriorTower();
        //Warrior can upgrade since we have the money
        assertTrue(warr.testUpgradeTower());
        info.setMoney(0);
        WarriorTower cannotUpgrade = new WarriorTower();
        //cannot upgrade since we have no money.
        assertFalse(cannotUpgrade.testUpgradeTower());
    }

    @Test
    @DisplayName("Buying a tower updates how much the player has spent")
    void testUpdatePlayerSpentTowers() {
        PlayerInformation singularUpdate = new PlayerInformation("xd", "Easy");
        WarriorTower warr = new WarriorTower();
        ArcherTower arch = new ArcherTower();
        WizardTower wizz = new WizardTower();

        warr.testBuyTower();
        assertEquals(250, singularUpdate.getMoneySpent());

        arch.testBuyTower();
        assertEquals(750, singularUpdate.getMoneySpent());

        wizz.testBuyTower();
        assertEquals(1500, singularUpdate.getMoneySpent());
    }

    @Test
    @DisplayName("Upgrading a tower updates how much the player has spent")
    void testUpdatePlayerSpentUpgrades() {
        WarriorTower warr = new WarriorTower();
        ArcherTower arch = new ArcherTower();
        WizardTower wizz = new WizardTower();

        warr.testUpgradeTower();
        assertEquals(250, info.getMoneySpent());

        arch.testUpgradeTower();
        assertEquals(750, info.getMoneySpent());

        wizz.testUpgradeTower();
        assertEquals(1500, info.getMoneySpent());
    }

    @Test
    @DisplayName("Purchasing tower updates the count to total towers bought")
    void testUpdateTowerTotal() {
        WarriorTower warr = new WarriorTower();
        ArcherTower arch = new ArcherTower();
        WizardTower wizz = new WizardTower();
        warr.testBuyTower();
        assertEquals(1, info.getTowersBought());
        arch.testBuyTower();
        assertEquals(2, info.getTowersBought());
        wizz.testBuyTower();
        assertEquals(3, info.getTowersBought());
    }

    @Test
    @DisplayName("When upgraded, the warrior should do more damage")
    void testWarrUpgradedDamage() {
        WarriorTower warr = new WarriorTower();
        WarriorTower notUpgraded = new WarriorTower();
        warr.testUpgradeTower();
        assertTrue(warr.getDamage() > notUpgraded.getDamage());

    }

    @Test
    @DisplayName("When upgraded, the archer tower should stun longer")
    void testArchUpgradedStun() {
        ArcherTower arch = new ArcherTower();
        ArcherTower notUpgraded = new ArcherTower();
        arch.testUpgradeTower();
        assertTrue(arch.getStunTime() > notUpgraded.getStunTime());
    }

    @Test
    @DisplayName("Upgrading the wizard tower yields more money")
    void testWizzUpgradedFarm() {
        WizardTower wizz = new WizardTower();
        WizardTower notUpgraded = new WizardTower();
        wizz.testUpgradeTower();
        assertTrue(wizz.getMoneyGrant() > notUpgraded.getMoneyGrant());
    }

    @Test
    @DisplayName("Defeating an enemy should update the total tally")
    void testEnemyKillTotal() {
        Zombie zomb = new Zombie();
        zomb.defeat();
        assertEquals(1, info.getEnemiesKilled());
        zomb.defeat();
        zomb.defeat();
        assertEquals(3, info.getEnemiesKilled());
    }

    @Test
    @DisplayName("Check if when the monument health hits zero, the game goes to the lose screen")
    void testLoseScreen() {
        info.setHealth(1);
        assertTrue(info.isAlive());
        info.testCheckDamage(10);
        //if alive is true, then we continue game
        //else when false, we end the game
        assertFalse(info.isAlive());
    }

    @Test
    @DisplayName("Upgrade costs change with difficulty")
    void testUpgradeCosts() {
        PlayerInformation easy = new PlayerInformation("haha", "Easy");
        WarriorTower warrEasy = new WarriorTower();
        warrEasy.testUpgradeTower();
        int easyUpgrade = easy.getMoneySpent();

        PlayerInformation medium = new PlayerInformation("haha", "Medium");
        WarriorTower warrMedium = new WarriorTower();
        warrMedium.testUpgradeTower();
        int mediumUpgrade = medium.getMoneySpent();

        PlayerInformation hard = new PlayerInformation("haha", "Hard");
        WarriorTower warrHard = new WarriorTower();
        warrHard.testUpgradeTower();
        int hardUpgrade = medium.getMoneySpent();

        assertEquals(250, easyUpgrade);
        assertEquals(375, mediumUpgrade);
        assertEquals(500, hardUpgrade);
    }
}