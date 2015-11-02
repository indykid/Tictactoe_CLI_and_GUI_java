package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameSelectionWidget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JFXVisualiserTest {

    private Scene scene;
    private JFXVisualiser visualiser;

    @Before
    public void setUp() throws Exception {
        scene = new Scene(new Pane());
        visualiser = new JFXVisualiser(scene);
    }

    @Test
    public void showsBoardWidget() {
        visualiser.displayBoardWidget(new Board(), null);

        assertTrue(visiblePartOf(scene) instanceof JFXBoardWidget);
    }

    @Test
    public void showsGameSelector() {
        setupJFXEnvironment();
        visualiser.displayGameSelectionWidget();

        assertTrue(visiblePartOf(scene) instanceof JFXGameSelectionWidget);
    }

    private JFXPanel setupJFXEnvironment() {
        return new JFXPanel();
    }

    private Parent visiblePartOf(Scene scene) {
        return scene.getRoot();
    }
}
