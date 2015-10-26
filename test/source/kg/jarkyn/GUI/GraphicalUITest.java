package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphicalUITest {

    @Test
    public void displaysGameSelector() {
        setupJFXEnvironment();
        Scene scene = new Scene(new StackPane());
        GraphicalUI ui = new GraphicalUI(scene);

        ui.displayGameSelector();

        assertEquals(3, getChildren(scene).size());
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }

    private List<Node> getChildren(Scene scene) {
        return scene.getRoot().getChildrenUnmodifiable();
    }
}