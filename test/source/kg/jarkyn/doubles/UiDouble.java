package kg.jarkyn.doubles;

import kg.jarkyn.Board;
import kg.jarkyn.Mark;
import kg.jarkyn.Ui;

import java.util.List;

public class UiDouble implements Ui {
    private int[] inputs;
    private int moveRequestCount;
    private boolean greetingWasDisplayed;
    private boolean boardWasDisplayed;
    private boolean notifiedOfInvalidInput;
    private boolean gameSelectionDisplayed;
    private boolean gameOverAnnounced;
    private boolean winnerAnnounced;
    private boolean drawAnnounced;

    public UiDouble(int[] inputs) {
        this.inputs = inputs;
        this.moveRequestCount = 0;
        this.greetingWasDisplayed = false;
        this.boardWasDisplayed = false;
        this.notifiedOfInvalidInput = false;
        this.gameSelectionDisplayed = false;
        this.gameOverAnnounced = false;
        this.winnerAnnounced = false;
        this.drawAnnounced = false;
    }

    @Override
    public void greet() {
        greetingWasDisplayed = true;
    }

    public boolean greetingWasDisplayed() {
        return greetingWasDisplayed;
    }

    @Override
    public void displayBoard(Board board) {
        boardWasDisplayed = true;
    }

    public boolean boardWasDisplayed() {
        return boardWasDisplayed;
    }

    @Override
    public int getMove(Mark mark, List<Integer> validMoves) {
        int input = getInput();

        return validateMove(input, mark, validMoves);
    }

    @Override
    public void notifyOfInvalidInput() {
        notifiedOfInvalidInput = true;
    }

    public boolean notifiedOfInvalidInput() {
        return notifiedOfInvalidInput;
    }

    @Override
    public int selectGame() {
        gameSelectionDisplayed = true;
        return getInput();
    }

    public boolean gameSelectionDisplayed() {
        return gameSelectionDisplayed;
    }

    @Override
    public void announceGameOver() {
        gameOverAnnounced = true;
    }

    public boolean gameOverAnnounced() {
        return gameOverAnnounced;
    }

    @Override
    public void announceWinner(Mark mark) {
        winnerAnnounced = true;
    }

    public boolean winnerAnnounced() {
        return winnerAnnounced;
    }

    @Override
    public void announceDraw() {
        drawAnnounced = true;
    }

    public boolean drawAnnounced() {
        return drawAnnounced;
    }

    private int getInput() {
        int input = inputs[moveRequestCount];
        moveRequestCount++;
        return input;
    }

    private int validateMove(int input, Mark mark, List<Integer> validMoves) {
        if (validMoves.contains(input - 1)) {
            return input - 1;
        } else {
            notifyOfInvalidInput();
            return getMove(mark, validMoves);
        }
    }
}