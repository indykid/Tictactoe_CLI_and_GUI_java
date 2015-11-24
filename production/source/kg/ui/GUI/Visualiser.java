package kg.ui.GUI;

import kg.jarkyn.Board;

public interface Visualiser {
    void displayGameSelectionWidget(GameOptionListener listener);

    void displayBoardWidget(Board board, PositionListener listener);
}
