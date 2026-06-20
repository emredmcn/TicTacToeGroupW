package org.example;

import java.util.Scanner;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            playSingleGame(scanner);
            playAgain = askPlayAgain(scanner);
        }
    }

    private void playSingleGame(Scanner scanner) {
        board.clear();
        currentPlayer = player1;
        while (true) {
            System.out.println("Current Player: " + currentPlayer.getMarker());
            board.print();
            if (!tryMove(scanner)) {
                continue;
            }
            if (board.hasWinner()) {
                board.print();
                System.out.println(currentPlayer.getMarker() + " wins!");
                return;
            }
            if (board.isFull()) {
                board.print();
                System.out.println("Draw!");
                return;
            }
            switchCurrentPlayer();
        }
    }

    private boolean tryMove(Scanner scanner) {
        int row = readCoordinate(scanner, "row (1-3): ") - 1;
        int col = readCoordinate(scanner, "column (1-3): ") - 1;
        try {
            board.place(row, col, currentPlayer.getMarker());
            return true;
        } catch (RuntimeException e) {
            System.out.println("Invalid move: " + e.getMessage());
            return false;
        }
    }

    private int readCoordinate(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    private boolean askPlayAgain(Scanner scanner) {
        System.out.print("New game? (y/n): ");
        String answer = scanner.hasNext() ? scanner.next() : "n";
        return answer.equalsIgnoreCase("y");
    }

    void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    boolean hasWinner() {
        return board.hasWinner();
    }

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    Board getBoard() {
        return board;
    }
}
