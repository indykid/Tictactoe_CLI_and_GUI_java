package kg.jarkyn.Core;

public class Game {
    protected Mark winnerMark;
    protected Board board;
    protected Player playerX;
    protected Player playerO;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    protected Player currentPlayer;

    public Game(Board board, Player playerX, Player playerO) {
        this.winnerMark    = Mark.NONE;
        this.board         = board;
        this.playerX       = playerX;
        this.playerO       = playerO;
        this.currentPlayer = playerX;
    }

    public boolean isOver() {
        return board.isFinalState();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWon() {
        return winnerMark() != Mark.NONE;
    }

    public Mark winnerMark() {
        if (winnerMark == Mark.NONE) {
            winnerMark = board.winnerMark();
        }
        return winnerMark;
    }

    public void playTurn() {
        addMove(currentPlayer.pickPosition(board));
        swapPlayers();
    }

    private void addMove(int position) {
        board = getBoard().addMove(position, currentPlayer.getMark());
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }
}
