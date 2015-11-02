package kg.jarkyn.GUI;

import javafx.scene.Scene;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameSelectionWidget;

public class JFXVisualiser implements Visualiser {
    private Scene scene;

    public JFXVisualiser(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void displayGameSelectionWidget(GameOptionListener listener) {
        scene.setRoot(new JFXGameSelectionWidget(listener));
    }

    @Override
    public void displayBoardWidget(Board board, PositionListener listener) {
        scene.setRoot(new JFXBoardWidget(board, listener));
    }

}
