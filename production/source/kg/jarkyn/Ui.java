package kg.jarkyn;

public interface Ui {
    int selectGame();

    void displayBoard(Board board);

//    int getMove(List<Integer> available);

    void announceGameOver();

    void announceDraw();

    void setGame(Game game);

    void playGame();
}
