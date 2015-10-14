package kg.jarkyn.doubles;

import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Game;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.Core.Ui;

import java.util.List;

public class UiDouble implements Ui {
    private int[] inputs;
    private int moveRequestCount;
    private boolean greetingWasDisplayed;
    private boolean boardWasDisplayed;
    private boolean gameSelectionDisplayed;
    private boolean gameOverAnnounced;
    private boolean winnerAnnounced;
    private boolean drawAnnounced;

    public UiDouble(int[] inputs) {
        this.inputs = inputs;
        this.moveRequestCount = 0;
        this.greetingWasDisplayed = false;
        this.boardWasDisplayed = false;
        this.gameSelectionDisplayed = false;
        this.gameOverAnnounced = false;
        this.winnerAnnounced = false;
        this.drawAnnounced = false;
    }

    public boolean greetingWasDisplayed() {
        return greetingWasDisplayed;
    }

    public void displayBoard(Board board) {
        boardWasDisplayed = true;
    }

    public boolean boardWasDisplayed() {
        return boardWasDisplayed;
    }

//    @Override
//    public int getMove(List<Integer> validMoves) {
//        return getInput() - 1;
//    }

    @Override
    public int selectGame() {
        greetingWasDisplayed = true;
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

    @Override
    public void setGame(Game game) {

    }

    @Override
    public int getMove(List<Integer> available) {
        return getInput() - 1;
    }

    @Override
    public void playGame() {

    }

    public boolean drawAnnounced() {
        return drawAnnounced;
    }

    private int getInput() {
        int input = inputs[moveRequestCount];
        moveRequestCount++;
        return input;
    }
}