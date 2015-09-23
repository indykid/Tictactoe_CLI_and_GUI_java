package kg.jarkyn;

public class Game {
    public Board getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Game game = new Game(new Board(), new HumanPlayer(Mark.X), new HumanPlayer(Mark.O), new CommandLineUi(new
                CommandLine(System.in, System
                .out)));
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

    public void play() {
        ui.greet();
        while (!isOver()) {
            playTurn();
        }
    }

    public boolean isOver() {
        return board.isFinalState();
    }

    public void playTurn() {
        ui.displayBoard(board);
        addMove(validPosition());
        swapPlayers();
    }

    private void addMove(int position) {
        this.board = board.addMove(position, currentPlayer.getMark());
    }

    private int validPosition() {
        int move = ui.getMove(currentPlayer.getMark());
        while (!board.isValidMove(move)) {
            ui.notifyOfInvalidInput();
            move = ui.getMove(currentPlayer.getMark());
        }
        return move;
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }
}
