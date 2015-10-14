package kg.jarkyn.Core;

import org.junit.Test;

import static kg.jarkyn.Core.Mark.*;
import static org.junit.Assert.assertEquals;

public class AiPlayerTest {
    @Test
    public void scoresWin() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {O, NONE, NONE,
                        X,   X,   X,
                        O, NONE, NONE};
        Board board = new Board(moves);

        assertEquals(10, ai.score(board, ai.mark));
    }

    @Test
    public void scoresLoss() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X,  X,    NONE,
                        O,  O,    O,
                        X,  NONE, NONE};
        Board board = new Board(moves);

        assertEquals(-10, ai.score(board, ai.mark));
    }

    @Test
    public void scoresDraw() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, O, X,
                        O, O, X,
                        X, X, O};
        Board board = new Board(moves);

        assertEquals(0, ai.score(board, ai.mark));
    }

    @Test
    public void nearWinHasWinScore() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, X, NONE,
                        O, O, X,
                        O, X, O};
        Board board = new Board(moves);

        assertEquals(10, ai.score(board, ai.mark));
    }

    @Test
    public void nearLossHasLossScore() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, X,    O,
                        X, X,    NONE,
                        O, NONE, O};
        Board board = new Board(moves);

        assertEquals(-10, ai.score(board, ai.mark.opponent()));
    }

    @Test
    public void nearDrawHasDrawScore() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, O, NONE,
                        O, O, X,
                        X, X, O};
        Board board = new Board(moves);

        assertEquals(0, ai.score(board, ai.mark));
    }

    @Test
    public void scoresIntermediateLosingState() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, O, NONE,
                        X, O, X,
                        NONE, NONE, NONE,};
        Board board = new Board(moves);

        assertEquals(-10, ai.score(board, ai.mark.opponent()));
    }

    @Test
    public void scoresIntermediateWinState() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, NONE, NONE,
                        O, NONE, NONE,
                        NONE, NONE, NONE,};
        Board board = new Board(moves);

        assertEquals(10, ai.score(board, ai.mark));
    }

    @Test
    public void scoresIntermediateDrawState() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X,    NONE, NONE,
                        NONE, O,    NONE,
                        NONE, NONE, NONE};
        Board board = new Board(moves);

        assertEquals(0, ai.score(board, ai.mark));
    }

    @Test
    public void takesWinningPosition() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {X, X, NONE,
                        O, O, NONE,
                        NONE, NONE, NONE,};
        Board board = new Board(moves);

        assertEquals(2, ai.pickPosition(board));
    }

    @Test
    public void blocksAsX() {
        AiPlayer ai = new AiPlayer(X);
        Mark[] moves = {NONE, NONE, NONE,
                        NONE, O,    NONE,
                        O,    X,    X};
        Board board = new Board(moves);

        assertEquals(2, ai.pickPosition(board));
    }

    @Test
    public void blocksAsO() {
        AiPlayer ai = new AiPlayer(O);
        Mark[] moves = {NONE, NONE, NONE,
                        NONE, X,    NONE,
                        X, NONE,    O};
        Board board = new Board(moves);

        assertEquals(2, ai.pickPosition(board));
    }
}
