package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void newCellIsEmpty() {
        Board board = new Board();
        assertTrue(board.isCellEmpty(0, 0));
    }

    @Test
    void occupiedCellIsNotEmpty() {
        Board board = new Board();
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));
    }

    @Test
    void placeOnEmptyCellSucceeds() {
        Board board = new Board();
        board.place(0, 2, 'O');
        assertFalse(board.isCellEmpty(0, 2));
    }

    @Test
    void placeOnOccupiedCellThrows() {
        Board board = new Board();
        board.place(2, 2, 'X');
        assertThrows(IllegalStateException.class, () -> board.place(2, 2, 'O'));
    }

    @Test
    void placeOutOfBoundsThrows() {
        Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> board.place(3, 0, 'X'));
    }

    @Test
    void printShowsEmptyBoard() {
        Board board = new Board();
        String output = capturePrint(board);
        assertTrue(output.contains("| | | |"));
    }

    @Test
    void printShowsPlacedMarker() {
        Board board = new Board();
        board.place(1, 1, 'X');
        String output = capturePrint(board);
        assertTrue(output.contains("X"));
    }

    @Test
    void emptyBoardIsNotFull() {
        Board board = new Board();
        assertFalse(board.isFull());
    }

    @Test
    void fullBoardIsFull() {
        Board board = new Board();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                board.place(x, y, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void noWinnerOnEmptyBoard() {
        Board board = new Board();
        assertFalse(board.hasWinner());
    }

    @Test
    void detectsRowWin() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(0, 1, 'X');
        board.place(0, 2, 'X');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsColumnWin() {
        Board board = new Board();
        board.place(0, 0, 'O');
        board.place(1, 0, 'O');
        board.place(2, 0, 'O');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsDiagonalWin() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(1, 1, 'X');
        board.place(2, 2, 'X');
        assertTrue(board.hasWinner());
    }

    private String capturePrint(Board board) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            board.print();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}
