package kg.jarkyn.GUI.SwingViewComponents;

import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.PositionListener;
import kg.jarkyn.doubles.PositionListenerDummy;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static kg.jarkyn.Core.Mark.NONE;
import static kg.jarkyn.Core.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwingBoardWidgetTest {

    private SwingBoardWidget widget;
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        widget = new SwingBoardWidget(board, new PositionListenerDummy());
    }

    @Test
    public void hasAsManyComponentsAsTheBoardGiven() {
        assertEquals(board.getSize(), widget.getComponents().length);
    }

    @Test
    public void hasCells() {
        assertTrue(componentsAreCells(widget));
    }

    @Test
    public void setsListenersOnCells() {
      assertTrue(listenersAreSet(widget));
    }

    @Test
    public void doesNotSetListenerIfCellIsPlayed() {
        Mark[] moves = {   X, NONE, NONE,
                        NONE, NONE, NONE,
                        NONE, NONE, NONE };
        Board board = new Board(moves);
        SwingBoardWidget widget = new SwingBoardWidget(board, new PositionListenerDummy());
        SwingCellWidget cell = (SwingCellWidget) widget.getComponent(0);

        assertEquals(0, cell.getActionListeners().length);
    }

    @Test
    public void sendsPositionToTheListener() {
        PositionListenerSpy listener = new PositionListenerSpy();
        SwingBoardWidget widget = new SwingBoardWidget(new Board(), listener);

        SwingCellWidget cell = (SwingCellWidget) widget.getComponent(0);
        cell.doClick();

        assertEquals(0, listener.positionReceived);
    }

    private boolean listenersAreSet(SwingBoardWidget widget) {
        for (Component component : widget.getComponents()) {
            if (component.getMouseListeners().length != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean componentsAreCells(SwingBoardWidget widget) {
        for (Component component : widget.getComponents()) {
            if (component.getClass() != SwingCellWidget.class) {
                return false;
            }
        }
        return true;
    }

    private class PositionListenerSpy implements PositionListener {
        private int positionReceived = -1;

        @Override
        public void positionSelected(int position) {
            this.positionReceived = position;
        }
    }
}
