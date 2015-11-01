package kg.jarkyn.Core;

import java.util.List;

public interface HumanInput {
//    int selectGame();
//
//    void announceDraw();

//    void playGame();

//    void setGame(Game game);

    int getMove(List<Integer> available);

//    void announceGameOver();

    boolean hasHumanMove();
}
