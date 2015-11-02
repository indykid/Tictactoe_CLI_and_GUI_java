package kg.jarkyn.GUI;

import kg.jarkyn.Core.Game;
import kg.jarkyn.Core.GameMaker;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.Core.HumanInput;

import java.util.List;

public class GraphicalUI implements HumanInput {

    private static final int NULL_MOVE = -1;
    private Visualiser visualiser;
    private Game game;
    private int humanMove = NULL_MOVE;

    public GraphicalUI(Visualiser visualiser) {
        this.visualiser = visualiser;
    }

    public void displayGameSelector() {
        visualiser.displayGameSelectionWidget(new GameOptionListener() {
            @Override
            public void gameOptionSelected(GameOption gameOption) {
                setupGame(gameOption);
                playGame();
            }
        });
    }

    public void displayBoard() {
        visualiser.displayBoardWidget(game.getBoard(), new PositionListener() {
            @Override
            public void positionSelected(int position) {
                setHumanMove(position);
                playGame();
            }
        });
    }

    public void playGame() {
        game.play();
        displayBoard();
    }

    @Override
    public int getMove(List<Integer> available) {
        int position = humanMove;
        humanMove = NULL_MOVE;
        return position;
    }

    @Override
    public boolean hasHumanMove() {
        return humanMove != NULL_MOVE;
    }

    void setHumanMove(int position) {
        humanMove = position;
    }

    void setupGame(GameOption gameOption) {
        game = GameMaker.makeGame(gameOption, this);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    Game getGame() {
        return game;
    }
}