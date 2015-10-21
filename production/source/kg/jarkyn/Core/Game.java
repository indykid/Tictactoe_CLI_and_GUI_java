package kg.jarkyn.Core;

public class Game {
    private Mark winnerMark;
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;

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

    public boolean isWon() {
        return winnerMark() != Mark.NONE;
    }

    public void playTurn() {
        if (currentPlayer.hasNextMove()) {
            addMove(currentPlayer.pickPosition(board));
            swapPlayers();
        }
    }

    public Mark winnerMark() {
        if (winnerMark == Mark.NONE) {
            winnerMark = board.winnerMark();
        }
        return winnerMark;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void addMove(int position) {
        board = getBoard().addMove(position, currentPlayer.getMark());
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }
}
