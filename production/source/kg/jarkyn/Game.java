package kg.jarkyn;

import java.io.IOException;

public class Game {
    private final Player playerX;
    private final Player playerO;
    private final Ui ui;
    private       Board board;
    private       Player currentPlayer;
    private       Mark winnerMark;

    public Game(Board board, Player playerX, Player playerO, Ui ui) {
        this.board         = board;
        this.playerX       = playerX;
        this.playerO       = playerO;
        this.ui            = ui;
        this.currentPlayer = playerX;
        this.winnerMark    = Mark.NONE;
    }

    public static void main(String[] args) {
        Ui ui = new CommandLineUi(new CommandLine(System.in, System.out));
        Game game = new GameSelector(ui).makeGame();
        game.play();
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
}