package kg.jarkyn.GUI.JFXViewComponents;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.PositionListener;

public class JFXBoardWidget extends GridPane {
    public JFXBoardWidget(Board board, PositionListener listener) {
        int position = 0;
        for (int y = 0; y < board.getDimension(); y++) {
            for (int x = 0; x < board.getDimension(); x++) {
                JFXCellWidget cell = new JFXCellWidget(position, board.markAt(position));

                if (board.markAt(position) == Mark.NONE) {
                    addListener(listener, cell);
                }
                add(cell, y, x);
                position++;
            }
        }
    }

    private void addListener(PositionListener listener, JFXCellWidget cell) {
        cell.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                listener.positionSelected(cell.getPosition());
            }
        });
    }

}
