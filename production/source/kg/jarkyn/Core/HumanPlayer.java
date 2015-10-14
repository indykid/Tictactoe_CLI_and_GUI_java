package kg.jarkyn.Core;

public class HumanPlayer extends Player {
    private final Ui ui;

    public HumanPlayer(Mark mark, Ui ui) {
        super(mark);
        this.ui = ui;
    }

    @Override
    public int pickPosition(Board board) {
        return ui.getMove(board.getAvailable());
    }
}