package kg.jarkyn.GUI;

import kg.jarkyn.Core.Board;

public interface Visualiser {
    void displayGameSelectionWidget(GameOptionListener listener);

    void displayBoardWidget(Board board, PositionListener listener);
}
