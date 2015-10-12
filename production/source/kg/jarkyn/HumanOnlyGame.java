package kg.jarkyn;

public class HumanOnlyGame extends Game {

    public HumanOnlyGame(Board board) {
        super(board, new HumanPlayer(Mark.X), new HumanPlayer(Mark.O));
    }

    public void playTurn(int position) {
        addMove(position);
        swapPlayers();
    }

    private void addMove(int position) {
        this.board = getBoard().addMove(position, currentPlayer.getMark());
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }
}