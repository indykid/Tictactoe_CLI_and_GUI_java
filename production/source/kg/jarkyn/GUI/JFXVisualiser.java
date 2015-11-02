package kg.jarkyn.GUI;

import javafx.scene.Scene;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;

public class JFXVisualiser {
    private Scene scene;

    public JFXVisualiser(Scene scene) {
        this.scene = scene;
    }

    public JFXBoardWidget makeBoardWidget(Board board, PositionListener listener) {
        return new JFXBoardWidget(board, listener);
    }

    public Scene getScene() {
        return scene;
    }

    public void displayBoardWidget(Board board, PositionListener listener) {
        scene.setRoot(makeBoardWidget(board, listener));
    }
}
