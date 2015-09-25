package kg.jarkyn;

import java.util.List;

public interface Ui {
    void greet();

    void displayBoard(Board board);

    int getMove(Mark mark, List<Integer> availableMoves);

    int selectGame(List<Integer> validOptions);

    void notifyOfInvalidInput();
}
