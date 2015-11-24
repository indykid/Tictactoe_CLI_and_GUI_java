package kg.ui.GUI.SwingViewComponents;

import kg.jarkyn.Board;
import kg.jarkyn.Mark;
import kg.ui.GUI.PositionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingBoardWidget extends JPanel {

    public SwingBoardWidget(Board board, PositionListener listener) {
        super(new GridLayout(0, board.getDimension()));
        setupCells(board, listener);

    }

    private void setupCells(Board board, PositionListener listener) {
        for (int position = 0; position < board.getSize(); position++) {
            SwingCellWidget cell = new SwingCellWidget(position, board.markAt(position));
            if (board.markAt(position) == Mark.NONE) {
                addListenerTo(cell, listener);
            }
            add(cell);
        }
    }

    private void addListenerTo(SwingCellWidget cell, PositionListener listener) {
        cell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.positionSelected(cell.getPosition());
            }
        });
    }
}
