package kg.ui.GUI;

import javafx.scene.Scene;
import kg.jarkyn.Board;
import kg.ui.GUI.JFXViewComponents.JFXBoardWidget;
import kg.ui.GUI.JFXViewComponents.JFXGameSelectionWidget;

public class JFXVisualiser implements Visualiser {
    private Scene scene;

    public JFXVisualiser(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void displayGameSelectionWidget(GameOptionListener listener) {
    }

    public void displayGameSelectionWidget(GraphicalUI ui) {
        scene.setRoot(new JFXGameSelectionWidget(ui));
    }

    @Override
    public void displayBoardWidget(Board board, PositionListener listener) {
        scene.setRoot(new JFXBoardWidget(board, listener));
    }

}
