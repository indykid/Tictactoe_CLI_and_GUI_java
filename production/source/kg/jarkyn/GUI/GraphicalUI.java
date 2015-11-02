package kg.jarkyn.GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import kg.jarkyn.Core.Game;
import kg.jarkyn.Core.GameMaker;
import kg.jarkyn.Core.HumanInput;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameOptionButton;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameSelectionWidget;

import java.util.List;

public class GraphicalUI implements HumanInput {

    private static final int NULL_MOVE = -1;
    private Scene scene;
    private Game game;
    private int humanMove = NULL_MOVE;

    public GraphicalUI(Scene scene) {
        this.scene = scene;
    }

    public void displayGameSelector() {
        Pane pane = new JFXGameSelectionWidget();
        for (Node button : pane.getChildren()) {
            addGameSelectionListener((JFXGameOptionButton) button);
        }
        scene.setRoot(pane);
    }

    private void addGameSelectionListener(JFXGameOptionButton button) {
        button.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
//                JFXGameOptionButton button = (JFXGameOptionButton) event.getSource();
                setupGame(button);
                playGame();
            }
        });
    }

    private void setupGame(JFXGameOptionButton button) {
        game = GameMaker.makeGame(button.getGameOption(), this);
    }

    public void displayBoard() {
        scene.setRoot(new JFXBoardWidget(game.getBoard(), position -> {
            setHumanMove(position);
            playGame();
        }));
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