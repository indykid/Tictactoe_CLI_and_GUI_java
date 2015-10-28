package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import kg.jarkyn.Core.*;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;
import kg.jarkyn.GUI.ViewComponents.MainPane;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphicalUITest {

    private Scene scene;
    private GraphicalUI ui;

    @Before
    public void setUp() throws Exception {
        scene = new Scene(new MainPane());
        ui = new GraphicalUI(scene);
    }

    @Test
    public void displaysGameSelector() {
        setupJFXEnvironment();

        ui.displayGameSelector();
        int amountOfGameOptions = GameOption.values().length;

        assertEquals(amountOfGameOptions, getChildren(scene).size());
    }

    @Test
    public void listenersAreSetOnGameSelectionButtons() {
        setupJFXEnvironment();

        ui.displayGameSelector();

        assertTrue(clickListenersAreSet(getChildren(scene)));
    }

    @Test
    public void setsUpGameOnSelection() {
        setupJFXEnvironment();

        ui.displayGameSelector();
        Node cell = getChildren(scene).get(0);
        cell.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertTrue(isGamePresent(ui));
    }

    @Test
    public void setsUpAiFirstGame() {
        setupJFXEnvironment();

        ui.displayGameSelector();
        Node aiFirstButton = findGameOption(getChildren(scene), GameOption.AI_FIRST);
        aiFirstButton.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertTrue(getCurrentPlayer(ui) instanceof AiPlayer);
    }

    @Test
    public void displaysBoard() {
        Board board = new Board();

        ui.displayBoard(board);

        int boardSize = board.getSize() * board.getSize();
        assertEquals(boardSize, getChildren(scene).size());
    }

    @Test
    public void setsUpListenersOnBoardCells() {
        ui.displayBoard(new Board());

        assertTrue(clickListenersAreSet(getChildren(scene)));
    }

    @Test
    public void playsClickedPosition() {
        setupJFXEnvironment();
        Game game = GameMaker.makeGame(GameOption.HUMAN_ONLY, ui);
        ui.setGame(game);
        ui.displayBoard(game.getBoard());

        Node cell = getChildren(scene).get(0);
        cell.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertEquals(Mark.X, game.getBoard().markAt(0));//!!!!
    }

    private Player getCurrentPlayer(GraphicalUI ui) {
        return ui.getGame().getCurrentPlayer();
    }

    private Node findGameOption(List<Node> nodes, GameOption option) {
        for (Node button : nodes) {
            if (((GameSelectionButton) button).getGameOption() == option) {
                return button;
            }
        }
        return null;
    }

    private boolean isGamePresent(GraphicalUI ui) {
        return ui.getGame() != null;
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