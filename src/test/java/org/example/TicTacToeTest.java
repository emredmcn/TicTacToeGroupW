package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class TicTacToeTest {

    @Test
    void switchChangesPlayer() {
        TicTacToe game = new TicTacToe();
        char first = game.getCurrentPlayer().getMarker();
        game.switchCurrentPlayer();
        assertNotEquals(first, game.getCurrentPlayer().getMarker());
    }

    @Test
    void switchTwiceReturnsToFirstPlayer() {
        TicTacToe game = new TicTacToe();
        char first = game.getCurrentPlayer().getMarker();
        game.switchCurrentPlayer();
        game.switchCurrentPlayer();
        assertEquals(first, game.getCurrentPlayer().getMarker());
    }

    @Test
    void hasWinnerFalseInitially() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.hasWinner());
    }

    @Test
    void hasWinnerTrueOnWinningLine() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'X');
        game.getBoard().place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void startAnnouncesWinner() {
        String output = runGame("1 1 2 1 1 2 2 2 1 3 n");
        assertTrue(output.contains("X wins!"));
    }

    @Test
    void startAnnouncesDraw() {
        String output = runGame("1 1 1 2 1 3 2 2 2 1 2 3 3 2 3 1 3 3 n");
        assertTrue(output.contains("Draw!"));
    }

    private String runGame(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            new TicTacToe().start();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return buffer.toString();
    }
}
