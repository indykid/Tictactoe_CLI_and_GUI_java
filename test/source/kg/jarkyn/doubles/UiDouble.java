package kg.jarkyn.doubles;

import kg.jarkyn.Board;
import kg.jarkyn.Mark;
import kg.jarkyn.Ui;

import java.util.List;
import java.util.stream.Collectors;

public class UiDouble implements Ui {
    private String[] inputs;
    private int     moveRequestCount;
    private boolean greetingWasDisplayed;
    private boolean boardWasDisplayed;
    private boolean notifiedOfInvalidInput;
    private boolean gameSelectionDisplayed;
    private boolean gameOverAnnounced;
    private boolean winnerAnnounced;
    private boolean drawAnnounced;

    public UiDouble(String input) {
        this.inputs                 = input.split("\n");
        this.moveRequestCount       = 0;
        this.greetingWasDisplayed   = false;
        this.boardWasDisplayed      = false;
        this.notifiedOfInvalidInput = false;
        this.gameSelectionDisplayed = false;
        this.gameOverAnnounced      = false;
        this.winnerAnnounced        = false;
        this.drawAnnounced          = false;
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
        String input = getInput();

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
    public int selectGame(List<Integer> gameOptions) {
        gameSelectionDisplayed = true;
        return Integer.parseInt(getInput());
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

    private String getInput() {
        String input = inputs[moveRequestCount];
        moveRequestCount++;
        return input;
    }

    private int validateMove(String input, Mark mark, List<Integer> validMoves) {
        try {
            int numericInput = Integer.parseInt(input);
            if (offset(validMoves).contains(numericInput)) {
                return numericInput - 1;
            } else {
                notifyOfInvalidInput();
                return getMove(mark, validMoves);
            }
        } catch (NumberFormatException e) {
            notifyOfInvalidInput();
            return getMove(mark, validMoves);
        }
    }

    private List<Integer> offset(List<Integer> available) {
        return available.stream().map(move -> move + 1).collect(Collectors.toList());
    }
}