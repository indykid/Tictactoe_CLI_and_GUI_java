package kg.jarkyn.Core;

public class HumanPlayer extends Player {
    private final HumanInput input;

    public HumanPlayer(Mark mark, HumanInput input) {
        super(mark);
        this.input = input;
    }

    @Override
    public int pickPosition(Board board) {
        return input.getMove(board.getAvailable());
    }

    @Override
    public boolean hasNextMove() {
        return input.hasHumanMove();
    }
}