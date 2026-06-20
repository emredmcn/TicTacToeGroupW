package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
