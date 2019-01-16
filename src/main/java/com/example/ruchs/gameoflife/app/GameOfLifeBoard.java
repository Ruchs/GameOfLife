package com.example.ruchs.gameoflife.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOfLifeBoard {

    private static final Random RANDOM = new Random();
    private int column;
    private int row;
    private Cell[][] gameBoard;

    public GameOfLifeBoard(int column, int row) {
        this.column = column;
        this.row = row;
        gameBoard = new Cell[column][row];
        init();
    }

    private void init() {
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                gameBoard[i][j] = new Cell(i, j, RANDOM.nextBoolean());
            }
        }
    }

    public Cell get(int i, int j) {
        return gameBoard[i][j];
    }

    public int neighboursOf(int i, int j) {
        int neighbour = 0;

        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                if ((ii != i || jj != j) && ii >= 0 && ii < column && jj >= 0 && jj < row) {
                    Cell cell = gameBoard[ii][jj];
                    if (cell.aliveCell) {
                        neighbour++;
                    }
                }
            }
        }
        return neighbour;
    }

    public void nextGeneration() {
        List<Cell> liveCells = new ArrayList<Cell>();
        List<Cell> deadCells = new ArrayList<Cell>();

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                Cell cell = gameBoard[i][j];
                int neighbours = neighboursOf(cell.xPosition, cell.yPosition);

                // Cell dies due to underpopulation
                if (cell.aliveCell && neighbours < 2) {
                    deadCells.add(cell);
                }

                // Cell dies due to overpopulation
                if (cell.aliveCell && neighbours < 3) {
                    deadCells.add(cell);
                }

                // Cell reborn
                if (!cell.aliveCell && neighbours == 3) {
                    liveCells.add(cell);
                }

                //Cell keep living
                if (cell.aliveCell && (neighbours == 2 || neighbours == 3)) {
                    deadCells.add(cell);
                }
            }
        }


        for (Cell cell : liveCells) {
            cell.reborn();
        }
        for (Cell cell : deadCells) {
            cell.dead();
        }
    }
}