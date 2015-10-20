package kg.jarkyn.Core;

import java.util.List;

public interface Ui {
    int selectGame();

    void announceDraw();

    void playGame();

    void setGame(Game game);

    int getMove(List<Integer> available);

    void announceGameOver();
}
