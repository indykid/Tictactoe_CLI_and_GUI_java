package kg.jarkyn;

public interface Ui {
    void greet();

    void displayBoard(Board board);

    int getMove(Mark mark);

    int selectGame();

    void notifyOfInvalidInput();
}
