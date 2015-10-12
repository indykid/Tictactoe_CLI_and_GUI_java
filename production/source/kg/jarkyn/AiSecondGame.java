package kg.jarkyn;

public class AiSecondGame extends Game {
    public AiSecondGame(Board board) {
        super(board, new HumanPlayer(Mark.X), new AiPlayer(Mark.O));
    }

    @Override
    void playTurn(int position) {
    // place Human's mark
        // tell ai to play
    }
}
