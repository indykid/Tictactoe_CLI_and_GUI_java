package kg.jarkyn.GUI;

import javafx.scene.Scene;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;
import kg.jarkyn.GUI.ViewComponents.GridCell;
import kg.jarkyn.GUI.ViewComponents.MainPane;

import java.util.List;

public class GraphicalUI {

    private Scene scene;

    public GraphicalUI(Scene scene) {
        this.scene = scene;
    }

    public void displayGameSelector() {
        MainPane pane = new MainPane();
        List<GameSelectionButton> buttons = ViewMaker.makeGameSelectionButtons();
        for (GameSelectionButton button : buttons) {
            pane.getChildren().add(button);
        }
        scene.setRoot(pane);
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
}