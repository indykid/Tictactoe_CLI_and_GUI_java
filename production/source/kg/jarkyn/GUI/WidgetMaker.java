package kg.jarkyn.GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;
import kg.jarkyn.GUI.ViewComponents.GridCell;
import kg.jarkyn.GUI.ViewComponents.MainGrid;
import kg.jarkyn.GUI.ViewComponents.MainPane;

public class WidgetMaker {
    public static MainPane makeGameSelectorWidget() {
        MainPane pane = new MainPane();
        pane.setPrefSize(600, 600);

        pane.setAlignment(Pos.CENTER_LEFT);
        int positionOffset = 0;
        for (GameOption gameOption : GameOption.values()) {
            GameSelectionButton button = new GameSelectionButton(gameOption);
            button.setTranslateX(positionOffset * 200);
            pane.getChildren().add(button);
            positionOffset++;
        }
        return pane;
    }

    public interface PositionListener {
        void positionSelected(int position);
    }

    public static MainGrid makeBoardWidget(Board board, PositionListener listener) {
        MainGrid grid = new MainGrid();
        grid.setPrefSize(600, 600);
        int position = 0;
        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                GridCell cell = new GridCell(position, board.markAt(position));

                if (board.markAt(position) == Mark.NONE) {
                    addListener(listener, cell);
                }
                grid.add(cell, y, x);
                position++;
            }
        }
        return grid;
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
