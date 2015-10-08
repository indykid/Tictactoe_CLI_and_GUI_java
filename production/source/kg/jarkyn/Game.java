package kg.jarkyn;

public class Game {
    private Player playerX;
    private Player playerO;
    private Ui ui;
    private Board board;
    private Player currentPlayer;
    private Mark winnerMark;

    public Game(Board board, Player playerX, Player playerO, Ui ui) {
        this.board         = board;
        this.playerX       = playerX;
        this.playerO       = playerO;
        this.ui            = ui;
        this.currentPlayer = playerX;
        this.winnerMark    = Mark.NONE;
    }

    public Game(int gameOptionValue) {
        setPlayers(gameOptionValue);
        this.board = new Board();
        this.currentPlayer = playerX;
        this.winnerMark = Mark.NONE;
    }

    public void play() {
        while (!isOver()) {
            playTurn();
        }
        announceResult();
    }

    public boolean isOver() {
        return board.isFinalState();
    }

    public void playTurn() {
        displayBoard();
        addMove(currentPlayer.pickPosition(board));
        swapPlayers();
    }

    public Board getBoard() {
        return board;
    }

    private void setPlayers(int gameOptionValue) {
        if (gameOptionValue == GameOption.AI_FIRST.value) {
            setPlayerX(new AiPlayer(Mark.X));
            setPlayerO(new HumanPlayer(Mark.O, ui));
        } else if (gameOptionValue == GameOption.AI_SECOND.value) {
            setPlayerX(new HumanPlayer(Mark.X, ui));
            setPlayerO(new AiPlayer(Mark.O));
        } else if (gameOptionValue == GameOption.HUMAN_ONLY.value) {
            setPlayerX(new HumanPlayer(Mark.X, ui));
            setPlayerO(new HumanPlayer(Mark.O, ui));
        }
    }

    private void displayBoard() {
        ui.displayBoard(board);
    }

    private void addMove(int position) {
        this.board = board.addMove(position, currentPlayer.getMark());
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }

    private void announceResult() {
        ui.announceGameOver();
        if (isWon()) {
            ui.announceWinner(board.winnerMark());
        } else {
            ui.announceDraw();
        }
    }

    private boolean isWon() {
        return winnerMark() != Mark.NONE;
    }

    private Mark winnerMark() {
        if (winnerMark == Mark.NONE) {
            winnerMark = board.winnerMark();
        }
        return winnerMark;
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }
}