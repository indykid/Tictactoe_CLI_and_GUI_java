package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameSelectionWidget;
import kg.jarkyn.doubles.GameOptionListenerDummy;
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
    public void displaysGameSelector() {
        setupJFXEnvironment();
        visualiser.displayGameSelectionWidget(new GameOptionListenerDummy());

        assertTrue(visibleNodeOf(scene) instanceof JFXGameSelectionWidget);
    }

    @Test
    public void displaysBoard() {
        visualiser.displayBoardWidget(new Board(), null);

        assertTrue(visibleNodeOf(scene) instanceof JFXBoardWidget);
    }

    private JFXPanel setupJFXEnvironment() {
        return new JFXPanel();
    }

    private Parent visibleNodeOf(Scene scene) {
        return scene.getRoot();
    }
}
