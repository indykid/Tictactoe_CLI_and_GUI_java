package kg.jarkyn;

import java.util.List;

public interface Ui {
    int selectGame();

    void displayBoard(Board board);

    int getMove(Mark mark, List<Integer> availableMoves);

    void announceGameOver();

    void announceWinner(Mark mark);

    void announceDraw();
}
