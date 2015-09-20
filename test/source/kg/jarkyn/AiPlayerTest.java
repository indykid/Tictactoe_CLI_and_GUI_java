package kg.jarkyn;

import org.junit.Test;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.assertEquals;

public class AiPlayerTest {
    @Test
    public void scoresWin() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {O, NONE, NONE,
                        X,   X,   X,
                        O, NONE, NONE};
        Board board = new Board(moves);

        assertEquals(10, ai.scoreFinalBoard(board));
    }

    @Test
    public void scoresLoss() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X,  X,    NONE,
                        O,  O,    O,
                        X,  NONE, NONE};
        Board board = new Board(moves);

        assertEquals(-10, ai.scoreFinalBoard(board));
    }

    @Test
    public void scoresDraw() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, O, X,
                        O, O, X,
                        X, X, O};
        Board board = new Board(moves);

        assertEquals(0, ai.scoreFinalBoard(board));
    }
}
