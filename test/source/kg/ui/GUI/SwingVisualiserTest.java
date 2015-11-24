package kg.ui.GUI;

import kg.jarkyn.Board;
import kg.ui.GUI.SwingViewComponents.SwingBoardWidget;
import kg.ui.GUI.SwingViewComponents.SwingGameSelectionWidget;
import kg.ui.doubles.GameOptionListenerDummy;
import kg.ui.doubles.PositionListenerDummy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwingVisualiserTest {

    private SwingVisualiser visualiser;

    @Before
    public void setUp() throws Exception {
        visualiser = new SwingVisualiser();
    }

    @Test
    public void displaysGameSelector() {
        visualiser.displayGameSelectionWidget(new GameOptionListenerDummy());

        assertEquals(SwingGameSelectionWidget.class, visiblePartOf(visualiser).getClass());
    }

    @Test
    public void displaysBoard() {
        visualiser.displayBoardWidget(new Board(), new PositionListenerDummy());

        assertEquals(SwingBoardWidget.class, visiblePartOf(visualiser).getClass());
    }

    private Object visiblePartOf(SwingVisualiser visualiser) {
        return visualiser.getFrame().getContentPane();
    }
}
