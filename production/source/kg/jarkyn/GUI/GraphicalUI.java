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

    private static final int NULL_MOVE = -1;
    private Scene scene;
    private Game game;
    private int humanMove = NULL_MOVE;

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
                GameSelectionButton button = (GameSelectionButton) event.getSource();
                setupGame(button);
            }
        });
    }

    private void setupGame(GameSelectionButton button) {
        game = GameMaker.makeGame(button.getGameOption(), this);
    }

    public void displayBoard(Board board) {
        MainPane pane = new MainPane();
        int positionCount = board.getSize()*board.getSize();
        for (int position = 0; position < positionCount; position++) {
            GridCell cell = new GridCell(position);
            addPlayPositionListener(cell);
            pane.getChildren().add(cell);
        }

        scene.setRoot(pane);
    }

    private void addPlayPositionListener(GridCell cell) {
        cell.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                GridCell cell = (GridCell) event.getSource();
                setHumanMove(cell.getPosition());
                playGame();
            }
        });
    }

    void setHumanMove(int position) {
        humanMove = position;
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
        game.play();
    }

    @Override
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
    public void announceGameOver() {

    }

    @Override
    public boolean hasHumanMove() {
        return humanMove != NULL_MOVE;
    }

    Game getGame() {
        return game;
    }
}