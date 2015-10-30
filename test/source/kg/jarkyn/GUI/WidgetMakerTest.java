package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.ViewComponents.GridCell;
import kg.jarkyn.GUI.ViewComponents.MainGrid;
import kg.jarkyn.GUI.ViewComponents.MainPane;
import org.junit.Test;


import static kg.jarkyn.Core.Mark.*;
import static org.junit.Assert.assertEquals;

public class WidgetMakerTest {
    class PositionListenerSpy implements WidgetMaker.PositionListener {
        public int receivedPosition;

        @Override
        public void positionSelected(int position) {
            this.receivedPosition = position;
        }
    }

    class PositionListenerDummy implements WidgetMaker.PositionListener {
        @Override
        public void positionSelected(int position) {
        }
    }

    @Test
    public void makesBoardWidget() {
        Board board = new Board();

        MainGrid pane = WidgetMaker.makeBoardWidget(board, new PositionListenerDummy());

        int boardSize = board.getSize() * board.getSize();
        assertEquals(boardSize, pane.getChildrenUnmodifiable().size());
    }

    @Test
    public void drawsMarksOnBoard() {
        Mark[] moves = {  X,  NONE, NONE,
                        NONE, NONE, NONE,
                        NONE, NONE, NONE};
        Board board = new Board(moves);

        MainGrid boardWidget = WidgetMaker.makeBoardWidget(board, new PositionListenerDummy());

        String cellText = ((GridCell) boardWidget.getChildren().get(0)).getText();
        assertEquals("X", cellText);
    }

    @Test
    public void sendsThePositionToTheListener() {
        PositionListenerSpy spy = new PositionListenerSpy();
        MainGrid pane = WidgetMaker.makeBoardWidget(new Board(), spy);
        Node firstCell = pane.getChildrenUnmodifiable().get(0);

        click(firstCell);

        assertEquals(0, spy.receivedPosition);
    }

    @Test
    public void makesGameSelectorWidget() {
        setupJFXEnvironment();
        int amountOfGameOptions = GameOption.values().length;

        MainPane pane = WidgetMaker.makeGameSelectorWidget();

        assertEquals(amountOfGameOptions, pane.getChildren().size());
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }

    private void click(Node node) {
        node.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));
    }

}