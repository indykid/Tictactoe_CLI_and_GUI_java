package kg.ui.doubles;


import kg.jarkyn.HumanInput;

import java.util.List;

public class InputDouble implements HumanInput {
    private int[] inputs;
    private int moveRequestCount;

    public InputDouble(int[] inputs) {
        this.inputs = inputs;
        this.moveRequestCount = 0;
    }

    @Override
    public boolean hasHumanMove() {
        return true;
    }

    @Override
    public int getMove(List<Integer> available) {
        return getInput() - 1;
    }

    private int getInput() {
        int input = inputs[moveRequestCount];
        moveRequestCount++;
        return input;
    }
}