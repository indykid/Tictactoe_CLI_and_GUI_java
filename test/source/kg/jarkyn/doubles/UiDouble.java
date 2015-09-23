package kg.jarkyn.doubles;

import kg.jarkyn.Board;
import kg.jarkyn.Mark;
import kg.jarkyn.Ui;

public class UiDouble implements Ui {
    private String[] inputs;
    private int moveRequestCount;
    private boolean greetingWasDisplayed;
    private boolean boardWasDisplayed;
    private boolean notifiedOfInvalidInput;

    public UiDouble(String input) {
        this.inputs                 = input.split("\n");
        this.moveRequestCount       = 0;
        this.greetingWasDisplayed   = false;
        this.boardWasDisplayed      = false;
        this.notifiedOfInvalidInput = false;
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
    public int getMove(Mark mark) {
        String input = inputs[moveRequestCount];
        moveRequestCount++;

        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            notifyOfInvalidInput();
            return getMove(mark);
        }
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
        return 0;
    }

    public boolean moveRequested() {
        return moveRequestCount != 0;
    }
}
