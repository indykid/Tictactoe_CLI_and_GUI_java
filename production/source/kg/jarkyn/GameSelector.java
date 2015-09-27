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

    public void makeGame() {
        ui.greet();
        this.gameType = ui.selectGame(Arrays.asList(1, 2, 3));
        switch (gameType) {
            case 1:
                setPlayerX(new AiPlayer(Mark.X));
                setPlayerO(new HumanPlayer(Mark.O, ui));

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
