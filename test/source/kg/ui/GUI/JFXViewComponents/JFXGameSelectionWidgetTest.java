package kg.ui.GUI.JFXViewComponents;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import kg.jarkyn.Board;
import kg.jarkyn.GameOption;
import kg.ui.GUI.GameOptionListener;
import kg.ui.GUI.GraphicalUI;
import kg.ui.GUI.PositionListener;
import kg.ui.GUI.Visualiser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JFXGameSelectionWidgetTest {

    private Pane gameSelectionWidget;
    private GraphicalUISpy ui;

    @Before
    public void setUp() throws Exception {
        setupJFXEnvironment();
        ui = new GraphicalUISpy();
        gameSelectionWidget = new JFXGameSelectionWidget(ui);
    }

    @Test
    public void hasAsManyButtonsAsGameOptionsAmount() {
        int amountOfGameOptions = GameOption.values().length;

        assertEquals(amountOfGameOptions, gameSelectionWidget.getChildren().size());
    }

    @Test
    public void setsClickListenersOnButtons() {
        assertTrue(clickListenersAreSet());
    }

    @Test
    public void sendsGameOptionToListener() {
        gameSelectionWidget = new JFXGameSelectionWidget(ui);
        Node aiFirstButton = getFirstButton();

        click(aiFirstButton);

        assertEquals(GameOption.AI_FIRST, ui.gameOptionReceived);
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }

    private void click(Node node) {
        node.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));
    }

    private boolean clickListenersAreSet() {
        for (Node node : gameSelectionWidget.getChildren()) {
            if (node.getOnMouseClicked() == null) {
                return false;
            }
        }
        return true;
    }

    private Node getFirstButton() {
        return gameSelectionWidget.getChildren().get(0);
    }

    private class GraphicalUISpy extends GraphicalUI {
        private GameOption gameOptionReceived;

        public GraphicalUISpy() {
            super(new VisualiserDummy());
        }

        @Override
        public void setupGame(GameOption gameOption) {
            super.setupGame(gameOption);
            gameOptionReceived = gameOption;
        }
    }

    private static class VisualiserDummy implements Visualiser {
        @Override
        public void displayGameSelectionWidget(GameOptionListener listener) {
        }

        @Override
        public void displayGameSelectionWidget(GraphicalUI ui) {
        }

        @Override
        public void displayBoardWidget(Board board, PositionListener listener) {
        }
    }
}
