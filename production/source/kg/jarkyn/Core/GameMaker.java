package kg.jarkyn.Core;

import static kg.jarkyn.Core.Mark.O;
import static kg.jarkyn.Core.Mark.X;

public class GameMaker {
    private static Player playerX;
    private static Player playerO;

    public static Game makeGame(GameOption gameOption, Ui ui) {
        if (gameOption == GameOption.AI_FIRST) {
            playerX = makeAiPlayer(X);
            playerO = makeHumanPlayer(O, ui);
        } else if (gameOption == GameOption.AI_SECOND) {
            playerX = new HumanPlayer(X, ui);
            playerO = new AiPlayer(O);
        } else {
            playerX = new HumanPlayer(X, ui);
            playerO = makeHumanPlayer(O, ui);
        }
        return new Game(new Board(), playerX, playerO);
    }

    public static Player getPlayerX() {
        return playerX;
    }

    public static Player getPlayerO() {
        return playerO;
    }

    private static HumanPlayer makeHumanPlayer(Mark mark, Ui ui) {
        return new HumanPlayer(mark, ui);
    }

    private static AiPlayer makeAiPlayer(Mark mark) {
        return new AiPlayer(mark);
    }
}
