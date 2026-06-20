package org.example;

public class Board {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private final char[][] cells;

    public Board() {
        cells = new char[SIZE][SIZE];
        clear();
    }

    public void clear() {
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

    public void print() {
        for (int row = 0; row < SIZE; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < SIZE; col++) {
                line.append('|').append(cells[row][col]);
            }
            line.append('|');
            System.out.println(line);
        }
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (cells[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (allEqual(cells[i][0], cells[i][1], cells[i][2])) {
                return true;
            }
            if (allEqual(cells[0][i], cells[1][i], cells[2][i])) {
                return true;
            }
        }
        if (allEqual(cells[0][0], cells[1][1], cells[2][2])) {
            return true;
        }
        return allEqual(cells[0][2], cells[1][1], cells[2][0]);
    }

    private boolean allEqual(char a, char b, char c) {
        return a != EMPTY && a == b && b == c;
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            throw new IllegalArgumentException("Coordinates out of range.");
        }
    }
}
