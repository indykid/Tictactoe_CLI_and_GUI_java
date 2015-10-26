package kg.jarkyn.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;

public class GraphicalUI {

    private Scene scene;

    public void displayGameSelector() {
        StackPane root = new StackPane();
        root.getChildren().add(new GameSelectionButton(GameOption.AI_FIRST));
        root.getChildren().add(new GameSelectionButton(GameOption.AI_SECOND));
        root.getChildren().add(new GameSelectionButton(GameOption.HUMAN_ONLY));
        scene = new Scene(root);

    }

    public Scene getScene() {
        return scene;
    }
}
