package kg.ui.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import kg.jarkyn.Board;
import kg.ui.GUI.JFXViewComponents.JFXBoardWidget;
import kg.ui.GUI.JFXViewComponents.JFXGameSelectionWidget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        GraphicalUI ui = new GraphicalUI(visualiser);
        visualiser.displayGameSelectionWidget(ui);

        assertTrue(visibleNodeOf(scene) instanceof JFXGameSelectionWidget);
    }

    @Test
    public void gameIsSetUpAAndPlayed() {
        setupJFXEnvironment();
        GraphicalUI ui = new GraphicalUI(visualiser);
        visualiser.displayGameSelectionWidget(ui);

        Node button = scene.lookup("#" + "AI_FIRST");
        button.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertNotNull(ui.getGame());
        Board board = ui.getGame().getBoard();
        assertEquals(board.getSize() - 1, board.getAvailable().size());
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
