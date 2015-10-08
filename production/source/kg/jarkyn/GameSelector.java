package kg.jarkyn;

public class GameSelector {
    private final Ui ui;
    private Player playerX;
    private Player playerO;
    private int gameType;

    public GameSelector(Ui ui) {
        this.ui = ui;
    }

    public Game makeGame() {
        selectGame();
        setPlayers();
        return new Game(new Board(), playerX, playerO, ui);
    }

    private void selectGame() {
        this.gameType = ui.selectGame();
    }

    private void setPlayers() {
        if (gameType == GameOption.AI_FIRST.value) {
            setPlayerX(new AiPlayer(Mark.X));
            setPlayerO(new HumanPlayer(Mark.O, ui));
        } else if (gameType == GameOption.AI_SECOND.value) {
            setPlayerX(new HumanPlayer(Mark.X, ui));
            setPlayerO(new AiPlayer(Mark.O));
        } else if (gameType == GameOption.HUMAN_ONLY.value) {
            setPlayerX(new HumanPlayer(Mark.X, ui));
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
