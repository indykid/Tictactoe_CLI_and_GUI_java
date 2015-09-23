package kg.jarkyn;

public class HumanPlayer extends Player {

    public HumanPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public int pickPosition(Board board) {
        return -1;
    }
}