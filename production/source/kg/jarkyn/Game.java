package kg.jarkyn;

public class Game {
    public Board getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Game game = new Game(new Board(), new Player("x"), new Player("o"), new Ui(new Cli(System.out, System.in)));
        game.play();
    }

    private Board board;
    private final Player playerX;
    private final Player playerO;
    private final Ui ui;
    private Player currentPlayer;

    public Game(Board board, Player playerX, Player playerO, Ui ui) {
        this.board = board;
        this.playerX = playerX;
        this.playerO = playerO;
        this.ui = ui;
        this.currentPlayer = playerX;
    }

    public boolean isOver() {
        return isDrawn() || isWon();
    }

    public void playTurn() {
        ui.displayBoard(board);
        addMove(validPosition());
        swapPlayers();
    }

    private int validPosition() {
        int move = ui.getMove(currentPlayer.mark);
        while (!board.isValidMove(move)) {
            ui.notifyOfInvalidInput();
            move = ui.getMove(currentPlayer.mark);
        }
        return move;
    }

    public void play() {
        ui.greet();
        while (!isOver()) {
            playTurn();
        }
    }

    private boolean isWon() {
        return board.isWon();
    }

    private boolean isDrawn() {
        return board.isFull() && !isWon();
    }

    private void addMove(int position) {
        this.board = board.addMove(position, currentPlayer.mark);
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }
}
