package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.ViewComponents.MainPane;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphicalUITest {

    @Test
    public void displaysGameSelector() {
        setupJFXEnvironment();
        Scene scene = new Scene(new StackPane());
        GraphicalUI ui = new GraphicalUI(scene);

        ui.displayGameSelector();
        int amountOfGameOptions = GameOption.values().length;

        assertEquals(amountOfGameOptions, getChildren(scene).size());
    }

    @Test
    public void listenersAreSetOnGameSelectionButtons() {
        setupJFXEnvironment();
        Scene scene = new Scene(new StackPane());
        GraphicalUI ui = new GraphicalUI(scene);

        ui.displayGameSelector();

        assertTrue(clickListenersAreSet(getChildren(scene)));
    }

    @Test
    public void setsUpGameOnSelection() {
        setupJFXEnvironment();
        Scene scene = new Scene(new MainPane());
        GraphicalUI ui = new GraphicalUI(scene);

        ui.displayGameSelector();
        Node cell = getChildren(scene).get(0);
        cell.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertTrue(ui.gamePresent());
    }

    @Test
    public void displaysBoard() {
        Scene scene = new Scene(new MainPane());
        GraphicalUI ui = new GraphicalUI(scene);
        Board board = new Board();

        ui.displayBoard(board);

        int boardSize = board.getSize() * board.getSize();
        assertEquals(boardSize, getChildren(scene).size());
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }

    private List<Node> getChildren(Scene scene) {
        return scene.getRoot().getChildrenUnmodifiable();
    }

    private boolean clickListenersAreSet(List<Node> elements) {
        for (Node element : elements) {
            if (element.onMouseClickedProperty().getValue() == null) {
                return false;
            }
        }
        return true;
    }
}