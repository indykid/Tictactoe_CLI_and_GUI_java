package kg.ui.GUI.SwingViewComponents;

import kg.jarkyn.GameOption;
import kg.ui.GUI.GameOptionListener;
import kg.ui.doubles.GameOptionListenerDummy;
import kg.ui.doubles.GameOptionListenerSpy;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwingGameSelectionWidgetTest {

    private GameOptionListener listener;
    private SwingGameSelectionWidget widget;

    @Before
    public void setUp() throws Exception {
        listener = new GameOptionListenerDummy();
        widget = new SwingGameSelectionWidget(listener);
    }

    @Test
    public void hasAsManyButtonsAsThereAreGameOptions() {
        assertEquals(GameOption.values().length, widget.getComponentCount());
    }

    @Test
    public void setsClickListeners() {
        assertTrue(clickListenersAreSet());
    }

    @Test
    public void sendsGameOptionToListener() {
        GameOptionListenerSpy listener = new GameOptionListenerSpy();
        SwingGameSelectionWidget widget = new SwingGameSelectionWidget(listener);

        SwingGameOptionButton aiFirstButton = (SwingGameOptionButton) widget.getComponent(0);
        aiFirstButton.doClick();

        assertEquals(GameOption.AI_FIRST, listener.gameOptionReceived);
    }

    private boolean clickListenersAreSet() {
        for (Component component : widget.getComponents()) {
            JButton button = (JButton) component;
            if (button.getActionListeners().length == 0) {
                return false;
            }
        }
        return true;
    }
}
