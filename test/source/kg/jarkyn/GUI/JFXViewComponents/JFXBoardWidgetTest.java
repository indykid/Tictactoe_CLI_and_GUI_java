package kg.jarkyn.GUI.JFXViewComponents;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.PositionListener;
import kg.jarkyn.doubles.PositionListenerDummy;
import org.junit.Test;

import static kg.jarkyn.Core.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JFXBoardWidgetTest {

    @Test
    public void hasAsManyCellsAsThePositionsOnTheGivenBoard() {
        Board board = new Board();
        JFXBoardWidget widget = new JFXBoardWidget(board, new PositionListenerDummy());

        assertEquals(board.getSize(), widget.getChildren().size());
    }

    @Test
    public void doesNotSetListenerIfCellIsPlayed() {
        Mark[] moves = {   X, NONE, NONE,
                        NONE, NONE, NONE,
                        NONE, NONE, NONE};
        Board board = new Board(moves);
        JFXBoardWidget widget = new JFXBoardWidget(board, new PositionListenerDummy());

        assertNull(widget.getChildren().get(0).getOnMouseClicked());
    }

    @Test
    public void sendsThePositionToTheListener() {
        PositionListenerSpy spy = new PositionListenerSpy();
        JFXBoardWidget pane = new JFXBoardWidget(new Board(), spy);
        Node firstCell = pane.getChildrenUnmodifiable().get(0);

        click(firstCell);

        assertEquals(0, spy.positionReceived);
    }

    private void click(Node node) {
        node.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));

    }

    private class PositionListenerSpy implements PositionListener {
        public int positionReceived = -1;

        @Override
        public void positionSelected(int position) {
            positionReceived = position;
        }
    }
}
