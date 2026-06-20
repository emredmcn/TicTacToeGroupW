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
