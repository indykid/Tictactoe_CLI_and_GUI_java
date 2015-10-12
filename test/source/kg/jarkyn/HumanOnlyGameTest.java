package kg.jarkyn;

import org.junit.Test;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.*;

public class HumanOnlyGameTest {

    @Test
    public void itIsNotOverAtTheStart() {
        HumanOnlyGame game = new HumanOnlyGame(new Board());

        assertFalse(game.isOver());
    }

    @Test
    public void itIsOverWhenDrawn() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, X, O};
        HumanOnlyGame game = new HumanOnlyGame(new Board(moves));

        assertTrue(game.isOver());
    }

    @Test
    public void itIsOverWhenWon() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        HumanOnlyGame game = new HumanOnlyGame(new Board(moves));

        assertTrue(game.isOver());
    }

    @Test
    public void noWinnerAtTheStart() {
        HumanOnlyGame game = new HumanOnlyGame(new Board());

        assertEquals(NONE, game.winnerMark());
    }

    @Test
    public void knowsWinner() {
        Mark[] moves = {X, O, X,
                X, X, O,
                O, O, X};
        HumanOnlyGame game = new HumanOnlyGame(new Board(moves));

        assertEquals(X, game.winnerMark());
    }

    @Test
    public void addsMoveToTheBoard() {
        HumanOnlyGame game = new HumanOnlyGame(new Board());

        game.playTurn(0);

        assertEquals(X, game.getBoard().markAt(0));
    }

    @Test
    public void swapsPlayers() {
        HumanOnlyGame game = new HumanOnlyGame(new Board());

        game.playTurn(0);
        game.playTurn(1);

        assertNotEquals(game.getBoard().markAt(0), game.getBoard().markAt(1));
    }
}