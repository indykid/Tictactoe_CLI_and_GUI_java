package kg.jarkyn.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GraphicalUI {

    private Scene scene;

    public void displayGameSelector() {
        StackPane root = new StackPane();
        root.getChildren().add(new Button());
        root.getChildren().add(new Button());
        root.getChildren().add(new Button());
        scene = new Scene(root);

    }

    public Scene getScene() {
        return scene;
    }
}
