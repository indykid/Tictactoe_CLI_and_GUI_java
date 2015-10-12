package kg.jarkyn;

public class AiFirstGame extends Game {
    public AiFirstGame(Board board) {
        super(board, new AiPlayer(Mark.X), new HumanPlayer(Mark.O));
        //play the move for Ai
    }

    @Override
    void playTurn(int position) {
        // place Human's mark
        // tell ai to play
    }
}
