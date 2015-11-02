package kg.jarkyn.GUI;

import javafx.scene.Scene;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameSelectionWidget;

public class JFXVisualiser {
    private Scene scene;

    public JFXVisualiser(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void displayGameSelectionWidget() {
        scene.setRoot(new JFXGameSelectionWidget());
    }

    public void displayBoardWidget(Board board, PositionListener listener) {
        scene.setRoot(new JFXBoardWidget(board, listener));
    }

}
