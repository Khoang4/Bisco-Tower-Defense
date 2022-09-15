package com.example.demo;


public class Grid {
    //Used an int grid, so 2 can flag start/end/corners etc.
    //coordinates are ordered as path[y][x]
    private final int[][] path = new int[][] {
            {0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}
    };

    //coordinates are ordered as path[y][x]
    private final int[][] buildableAreas = new int[][] {
            {0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0}
    };

    //Currently, grid simply states if tower is present
    //Can edit to store tower type as well
    private static int[][] towers = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };  //coordinates are ordered as path[y][x]

    public Grid() {
        //can set up instantiation here if necessary
    }

    public boolean getPath(int x, int y) {
        if (x > 15 || y > 8 || x < 0 || y < 0) {
            System.out.println("Outside bounds of map");
            return false;
        } else {
            return (path[y][x] == 1);
        }
    }

    public boolean getBuildable(int x, int y) {
        if (x > 15 || y > 8 || x < 0 || y < 0) {
            System.out.println("Outside bounds of map");
            return false;
        } else {
            return (buildableAreas[y][x] == 1);
        }
    }

    public int getTower(int x, int y) {
        if (x > 15 || y > 8 || x < 0 || y < 0) {
            // System.out.println("Outside bounds of map");
            return 0;
        } else {
            return (towers[y][x]);
        }
    }

    public boolean addTower(int x, int y, int num) {
        if (x > 15 || y > 8 || x < 0 || y < 0) {
            return false;
        } else if (this.getBuildable(x, y) && towers[y][x] != 1) {
            towers[y][x] = num;
            return true;
        } else {
            return false;
        }
    }

    public boolean upgradeTower(int x, int y, int num) {
        if (x > 15 || y > 8 || x < 0 || y < 0) {
            return false;
        } else if (this.getBuildable(x, y) && towers[y][x] == (num - 3)) {
            towers[y][x] = num;
            return true;
        } else {
            return false;
        }
    }

    public void resetTowerGrid() {
        towers = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    }
}
