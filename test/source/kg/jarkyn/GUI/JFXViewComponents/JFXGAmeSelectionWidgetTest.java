package kg.jarkyn.GUI.JFXViewComponents;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.GameOptionListener;
import kg.jarkyn.doubles.GameOptionListenerDummy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JFXGameSelectionWidgetTest {

    private Pane gameSelectionWidget;

    @Before
    public void setUp() throws Exception {
        setupJFXEnvironment();
        gameSelectionWidget = new JFXGameSelectionWidget(new GameOptionListenerDummy());
    }

    @Test
    public void hasAsManyButtonsAsGameOptionsAmount() {
        int amountOfGameOptions = GameOption.values().length;

        assertEquals(amountOfGameOptions, gameSelectionWidget.getChildren().size());
    }

    @Test
    public void buttonsHaveCorrectDescription() {
        Button aiFirstButton = (Button) getFirstButton();

        assertEquals(GameOption.AI_FIRST.readableOption(), aiFirstButton.getText());
    }

    @Test
    public void setsClickListenersOnButtons() {
        assertTrue(clickListenersAreSet());
    }

    @Test
    public void sendsGameOptionToListener() {
        GameOptionListenerSpy spy = new GameOptionListenerSpy();
        gameSelectionWidget = new JFXGameSelectionWidget(spy);
        Node aiFirstButton = getFirstButton();

        click(aiFirstButton);

        assertEquals(GameOption.AI_FIRST, spy.gameOptionReceived);
    }

    private class GameOptionListenerSpy implements GameOptionListener {
        public GameOption gameOptionReceived;

        @Override
        public void gameOptionSelected(GameOption gameOption) {
            gameOptionReceived = gameOption;
        }
    }

    private void click(Node node) {
        node.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
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
}
