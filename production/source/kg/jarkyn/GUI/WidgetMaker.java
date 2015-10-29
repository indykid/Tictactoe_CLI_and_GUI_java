package kg.jarkyn.GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;
import kg.jarkyn.GUI.ViewComponents.GridCell;
import kg.jarkyn.GUI.ViewComponents.MainPane;

public class WidgetMaker {
    public static MainPane makeGameSelectorWidget() {
        MainPane pane = new MainPane();
        for (GameOption gameOption : GameOption.values()) {
            pane.getChildren().add(new GameSelectionButton(gameOption));
        }
        return pane;
    }

    public interface PositionListener {
        void positionSelected(int position);
    }

    public static MainPane makeBoardWidget(Board board, PositionListener listener) {
        MainPane pane = new MainPane();
        int positionCount = board.getSize()*board.getSize();
        for (int position = 0; position < positionCount; position++) {
            GridCell cell = new GridCell(position, board.markAt(position));
            if (board.markAt(position) == Mark.NONE) {
                addListener(listener, cell);
            }
            pane.getChildren().add(cell);
        }
        return pane;
    }

    private static void addListener(final PositionListener listener, GridCell cell) {
        cell.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                GridCell cell = (GridCell) event.getSource();
                int position = cell.getPosition();
                listener.positionSelected(position);
            }
        });
    }
}
