package kg.jarkyn;

public class Game {
    public Board getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Ui ui = new CommandLineUi(new CommandLine(System.in, System.out));
        GameSelector gameSelector = new GameSelector(ui);
        Game game = gameSelector.makeGame();
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
        while (!isOver()) {
            playTurn();
        }
    }

    public boolean isOver() {
        return board.isFinalState();
    }

    public void playTurn() {
        ui.displayBoard(board);
        addMove(currentPlayer.pickPosition(board));
        swapPlayers();
    }

    private void addMove(int position) {
        this.board = board.addMove(position, currentPlayer.getMark());
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }
}
