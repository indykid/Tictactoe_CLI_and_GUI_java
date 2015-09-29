package kg.jarkyn;

import java.util.Arrays;

public class GameSelector {
    private final Ui ui;
    private Player playerX;
    private Player playerO;
    private int gameType;

    public GameSelector(Ui ui) {
        this.ui = ui;
    }

    public Game makeGame() {
        ui.greet();
        selectGame();
        setPlayers();
        return new Game(new Board(), playerX, playerO, ui);
    }

    private void selectGame() {
        this.gameType = ui.selectGame();
    }

    private void setPlayers() {
        switch (gameType) {
            case 1:
                setPlayerX(new AiPlayer(Mark.X));
                setPlayerO(new HumanPlayer(Mark.O, ui));
                break;
            case 2:
                setPlayerX(new HumanPlayer(Mark.X, ui));
                setPlayerO(new AiPlayer(Mark.O));
                break;
            case 3:
                setPlayerX(new HumanPlayer(Mark.X, ui));
                setPlayerO(new HumanPlayer(Mark.O, ui));
                break;
        }
    }

    public int getGameType() {
        return gameType;
    }

    public Player getPlayerX() {
        return this.playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    private void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }
}
