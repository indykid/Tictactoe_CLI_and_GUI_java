package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import kg.jarkyn.Core.*;
import kg.jarkyn.GUI.JFXViewComponents.JFXGameOptionButton;
import kg.jarkyn.GUI.JFXViewComponents.JFXCellWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXGrid;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GraphicalUITest {

    private Scene scene;
    private GraphicalUI ui;
    private JFXVisualiser visualiser;

    @Before
    public void setUp() throws Exception {
        scene = new Scene(new JFXGrid());
        visualiser = new JFXVisualiser(scene);
        ui = new GraphicalUI(visualiser);
        setupJFXEnvironment();
    }

    @Test
    public void listenersAreSetOnGameSelectionButtons() {
        ui.displayGameSelector();

        assertTrue(clickListenersAreSet(getChildren(scene)));
    }

    @Test
    public void setsUpGameOnSelection() {
        ui.displayGameSelector();

        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertTrue(isGamePresent(ui));
    }

    @Test
    public void setsUpGameAccordingToTheGameSelected() {
        ui.displayGameSelector();
        Node aiSecondButton = findButton(scene, GameOption.AI_SECOND);

        aiSecondButton.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertTrue(getCurrentPlayer(ui) instanceof HumanPlayer);
    }

    @Test
    public void aiPlaysFirstMoveWhenAiFirstGame() {
        ui.displayGameSelector();
        Node aiFirstButton = findButton(scene, GameOption.AI_FIRST);

        aiFirstButton.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertEquals("X", ((JFXCellWidget) getFirstChild()).getText());
    }

    @Test
    public void displaysBoardOnGameSelection() {
        ui.displayGameSelector();

        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertEquals(9, getChildren(scene).size());
    }

    @Test
    public void playsClickedPosition() {
        Game game = GameMaker.makeGame(GameOption.HUMAN_ONLY, ui);
        ui.setGame(game);
        ui.displayBoard();

        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertEquals(Mark.X, game.getBoard().markAt(0));
    }

    @Test
    public void drawsMarkToTheClickedCell() {
        ui.setGame(GameMaker.makeGame(GameOption.HUMAN_ONLY, ui));
        ui.displayBoard();

        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertEquals("X", ((JFXCellWidget) getFirstChild()).getText());
    }

    @Test
    public void doesNotSetListenerIfCellIsPlayed() {
        ui.setGame(GameMaker.makeGame(GameOption.HUMAN_ONLY, ui));
        ui.displayBoard();

        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertNull(getFirstChild().onMouseClickedProperty().getValue());
    }

    @Test
    public void nothingHappensWhenClickingIntoPlayedCell() {
        Game game = GameMaker.makeGame(GameOption.HUMAN_ONLY, ui);
        ui.setGame(game);
        ui.displayBoard();

        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));
        getFirstChild().fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

        assertEquals(Mark.X, game.getBoard().markAt(0));
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }

    private boolean clickListenersAreSet(List<Node> elements) {
        for (Node element : elements) {
            if (element.getOnMouseClicked() == null) {
                return false;
            }
        }
        return true;
    }

    private Player getCurrentPlayer(GraphicalUI ui) {
        return ui.getGame().getCurrentPlayer();
    }

    private Node findButton(Scene scene, GameOption option) {
        for (Node button : getChildren(scene)) {
            if (((JFXGameOptionButton) button).getGameOption() == option) {
                return button;
            }
        }
        return null;
    }

    private boolean isGamePresent(GraphicalUI ui) {
        return ui.getGame() != null;
    }

    private List<Node> getChildren(Scene scene) {
        return scene.getRoot().getChildrenUnmodifiable();
    }

    private Node getFirstChild() {
        return getChildren(scene).get(0);
    }
}