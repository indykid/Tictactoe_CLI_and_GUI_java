package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.ViewComponents.MainPane;
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
        int amountOfGameOptions = GameOption.values().length;

        assertEquals(amountOfGameOptions, getChildren(scene).size());
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
}