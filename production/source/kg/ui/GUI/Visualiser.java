package kg.ui.GUI;

import kg.jarkyn.Board;

public interface Visualiser {
    void displayGameSelectionWidget(GameOptionListener listener);
    void displayGameSelectionWidget(GraphicalUI ui);

    void displayBoardWidget(Board board, PositionListener listener);
}
