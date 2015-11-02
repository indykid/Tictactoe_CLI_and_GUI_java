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

    private void setupGame(GameOption gameOption) {
        game = GameMaker.makeGame(gameOption, this);

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

    void setHumanMove(int position) {
        humanMove = position;
    }

    public void playGame() {
        game.play();
        displayBoard();
    }

    public void setGame(Game game) {
        this.game = game;
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

    Game getGame() {
        return game;
    }
}