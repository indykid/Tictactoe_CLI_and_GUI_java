package kg.jarkyn.doubles;

import kg.jarkyn.Core.Game;
import kg.jarkyn.Core.Ui;

import java.util.List;

public class UiDouble implements Ui {
    private int[] inputs;
    private int moveRequestCount;

    public UiDouble(int[] inputs) {
        this.inputs = inputs;
        this.moveRequestCount = 0;
    }

    @Override
    public int selectGame() {
        return getInput();
    }

    @Override
    public void announceGameOver() {
    }

    @Override
    public boolean hasHumanMove() {
        return true;
    }

    @Override
    public void announceDraw() {
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

    private int getInput() {
        int input = inputs[moveRequestCount];
        moveRequestCount++;
        return input;
    }
}