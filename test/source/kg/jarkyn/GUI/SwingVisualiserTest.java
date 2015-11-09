package kg.jarkyn.GUI;

import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.SwingViewComponents.SwingBoardWidget;
import kg.jarkyn.GUI.SwingViewComponents.SwingGameSelectionWidget;
import kg.jarkyn.doubles.GameOptionListenerDummy;
import kg.jarkyn.doubles.PositionListenerDummy;
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
