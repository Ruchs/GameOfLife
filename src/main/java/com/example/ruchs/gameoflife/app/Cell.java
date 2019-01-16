package com.example.ruchs.gameoflife.app;

public class Cell {
    public int xPosition;
    public int yPosition;
    public boolean aliveCell;

    public Cell(int xPosition, int yPosition, boolean aliveCell) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.aliveCell = aliveCell;
    }

    public void dead() {
        aliveCell = false;
    }

    public void reborn() {
        aliveCell = true;
    }
}
