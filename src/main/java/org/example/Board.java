package org.example;

public class Board {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private final char[][] cells;

    public Board() {
        cells = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = EMPTY;
            }
        }
    }

    public boolean isCellEmpty(int x, int y) {
        checkBounds(x, y);
        return cells[x][y] == EMPTY;
    }

    public void place(int x, int y, char marker) {
        checkBounds(x, y);
        if (cells[x][y] != EMPTY) {
            throw new IllegalStateException("Cell is already occupied.");
        }
        cells[x][y] = marker;
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            throw new IllegalArgumentException("Coordinates out of range.");
        }
    }
}
