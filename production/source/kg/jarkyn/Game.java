package kg.jarkyn;

public abstract class Game {
    protected Board board;
    protected Mark winnerMark;
    protected Player playerX;
    protected Player playerO;
    protected Player currentPlayer;

    public Game(Board board, Player playerX, Player playerO) {
        this.winnerMark    = Mark.NONE;
        this.board         = board;
        this.playerX       = playerX;
        this.currentPlayer = playerX;
        this.playerO       = playerO;
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

    abstract void playTurn(int position);
}
