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
    private int     gameType;

    public UiDouble(String input) {
        this.inputs                 = input.split("\n");
        this.moveRequestCount       = 0;
        this.greetingWasDisplayed   = false;
        this.boardWasDisplayed      = false;
        this.notifiedOfInvalidInput = false;
        this.gameSelectionDisplayed = false;
        this.gameType               = 0;
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
        String input = inputs[moveRequestCount];
        moveRequestCount++;

        return validate(input, mark, validMoves);
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
        return 0;
    }

    public boolean gameSelectionDisplayed() {
        return gameSelectionDisplayed;
    }

    public boolean receivedGameType() {
        return gameType != 0;
    }

    private int validate(String input, Mark mark, List<Integer> validMoves) {
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
