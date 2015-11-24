package kg.ui.GUI;

import kg.jarkyn.Board;
import kg.ui.GUI.SwingViewComponents.SwingBoardWidget;
import kg.ui.GUI.SwingViewComponents.SwingGameSelectionWidget;

import javax.swing.*;

public class SwingVisualiser implements Visualiser {

    private JFrame frame;

    public SwingVisualiser() {
        setupFrame();
    }

    private void setupFrame() {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.setVisible(true);
    }

    @Override
    public void displayGameSelectionWidget(GameOptionListener listener) {
        frame.setContentPane(new SwingGameSelectionWidget(listener));
        frame.pack();
    }

    @Override
    public void displayBoardWidget(Board board, PositionListener listener) {
        frame.setContentPane(new SwingBoardWidget(board, listener));
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }
}
