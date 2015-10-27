package kg.jarkyn.GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Game;
import kg.jarkyn.Core.GameMaker;
import kg.jarkyn.Core.Ui;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;
import kg.jarkyn.GUI.ViewComponents.GridCell;
import kg.jarkyn.GUI.ViewComponents.MainPane;

import java.util.List;

public class GraphicalUI implements Ui {

    private Scene scene;
    private Game game;

    public GraphicalUI(Scene scene) {
        this.scene = scene;
    }

    public void displayGameSelector() {
        MainPane pane = new MainPane();
        List<GameSelectionButton> buttons = ViewMaker.makeGameSelectionButtons();
        for (GameSelectionButton button : buttons) {
            addGameSelectionListener(button);
            pane.getChildren().add(button);
        }
        scene.setRoot(pane);
    }

    private void addGameSelectionListener(GameSelectionButton button) {
        button.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                setupGame(event);
            }
        });
    }

    private void setupGame(Event event) {
        GameSelectionButton button = (GameSelectionButton) event.getSource();
        game = GameMaker.makeGame(button.getGameOption(), this);
    }

    public void displayBoard(Board board) {
        MainPane pane = new MainPane();
        int positionCount = board.getSize()*board.getSize();
        for (int position = 0; position < positionCount; position++) {
            GridCell cell = new GridCell(position);
            pane.getChildren().add(cell);
        }

        scene.setRoot(pane);
    }

    @Override
    public int selectGame() {
        return 0;
    }

    @Override
    public void announceDraw() {

    }

    @Override
    public void playGame() {

    }

    @Override
    public void setGame(Game game) {

    }

    @Override
    public int getMove(List<Integer> available) {
        return 0;
    }

    @Override
    public void announceGameOver() {

    }

    @Override
    public boolean hasHumanMove() {
        return false;
    }

    public Game getGame() {
        return game;
    }
}