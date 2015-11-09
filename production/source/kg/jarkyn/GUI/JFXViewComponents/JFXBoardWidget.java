package kg.jarkyn.GUI.JFXViewComponents;

import javafx.event.Event;
import javafx.event.EventHandler;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.GUI.PositionListener;

public class JFXBoardWidget extends JFXGrid {

    public JFXBoardWidget(Board board, PositionListener listener) {
        setupCells(board, listener);
    }

    private void setupCells(Board board, PositionListener listener) {
        int position = 0;
        for (int y = 0; y < board.getDimension(); y++) {
            for (int x = 0; x < board.getDimension(); x++) {
                JFXCellWidget cell = new JFXCellWidget(position, board.markAt(position));

                if (board.markAt(position) == Mark.NONE) {
                    addListenerTo(cell, listener);
                }
                add(cell, y, x);
                position++;
            }
        }
    }

    private void addListenerTo(JFXCellWidget cell, PositionListener listener) {
        cell.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                listener.positionSelected(cell.getPosition());
            }
        });
    }
}
