package kg.jarkyn.GUI.JFXViewComponents;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.GUI.JFXViewComponents.JFXCellWidget;
import org.junit.Test;

import static kg.jarkyn.Core.Mark.*;
import static org.junit.Assert.assertEquals;

public class JFXBoardWidgetTest {
    class PositionListenerDummy implements JFXBoardWidget.PositionListener {
        @Override
        public void positionSelected(int position) {
        }
    }
    @Test
    public void hasAsManyChildElementsAsThePositionsOnTheGivenBoard() {
        Board board = new Board();
        JFXBoardWidget widget = new JFXBoardWidget(board, new PositionListenerDummy());

        assertEquals(board.getSize(), widget.getChildren().size());
    }

    @Test
    public void drawsMarksOnBoard() {
        Mark[] moves = {   X, NONE, NONE,
                        NONE, NONE, NONE,
                        NONE, NONE, NONE};
        Board board = new Board(moves);

        JFXBoardWidget widget = new JFXBoardWidget(board, new PositionListenerDummy());

        String cellText = ((JFXCellWidget) widget.getChildren().get(0)).getText();
        assertEquals("X", cellText);
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

    private class PositionListenerSpy implements JFXBoardWidget.PositionListener {
        public int positionReceived = -1;

        @Override
        public void positionSelected(int position) {
            positionReceived = position;
        }
    }
}
