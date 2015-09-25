package kg.jarkyn;

import org.junit.Test;
import kg.jarkyn.doubles.UiDouble;

import java.util.Arrays;

import static org.junit.Assert.*;
import static kg.jarkyn.Mark.*;

public class GameTest {
    private UiDouble ui;

    private Game setupGame(Board board, String input) {
        ui = new UiDouble(input);
        return new Game(board, new HumanPlayer(X, ui), new HumanPlayer(O, ui), ui);
    }

    @Test
    public void itIsNotOverAtTheStart() {
        Game game = setupGame(new Board(), "irrelevant");

        assertFalse(game.isOver());
    }

    @Test
    public void itIsOverWhenDrawn() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, X, O};
        Game game = setupGame(new Board(moves), "irrelevant");

        assertTrue(game.isOver());
    }

    @Test
    public void itIsOverWhenWon() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game = setupGame(new Board(moves), "irrelevant");

        assertTrue(game.isOver());
    }

    @Test
    public void greets() {
        Game game = setupGame(new Board(), ("1\n4\n2\n5\n3"));

        game.play();

        assertTrue(ui.greetingWasDisplayed());
    }

    @Test
    public void displaysBoard() {
        Game game = setupGame(new Board(), "1");

        game.playTurn();

        assertTrue(ui.boardWasDisplayed());
    }

    @Test
    public void addsMoveToTheBoard() {
        Game game = setupGame(new Board(), "1");

        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
    }

    @Test
    public void swapsPlayers() {
        Game game = setupGame(new Board(), "1\n2");

        game.playTurn();
        game.playTurn();

        assertNotEquals(game.getBoard().markAt(0), game.getBoard().markAt(1));
    }

    @Test
    public void playsTillWon() {
        Game game = setupGame(new Board(), "1\n4\n2\n5\n3");

        game.play();

        assertTrue(game.isOver());
    }

    @Test
    public void playsTillDrawn() {
        Game game = setupGame(new Board(), "1\n2\n3\n5\n4\n6\n8\n7\n9");

        game.play();

        assertTrue(game.isOver());
    }

    @Test
    public void doesNotPlayIntoOccupiedPosition() {
        Game game = setupGame(new Board(), ("1\n1\n2"));

        game.playTurn();
        game.playTurn();



        assertEquals(X, game.getBoard().markAt(0));
        assertEquals(O, game.getBoard().markAt(1));
    }

    @Test
    public void doesNotPlayIntoNonExistingPosition() {
        Game game = setupGame(new Board(), "10\n1\n2") ;

        game.playTurn();
        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
        assertEquals(O, game.getBoard().markAt(1));
    }

    @Test
    public void doesNotPlayIntoInvalidPosition() {
        Game game = setupGame(new Board(), "a\n1\n2");

        game.playTurn();
        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
        assertEquals(O, game.getBoard().markAt(1));
    }

    @Test
    public void notifiesOfInvalidInputForNonNumericMove() {
        Game game = setupGame(new Board(), "a\n5");

        game.playTurn();

        assertTrue(ui.notifiedOfInvalidInput());
    }

    @Test
    public void notifiesOfInvalidMoveIntoOccupiedPosition() {
        Game game = setupGame(new Board(), "1\n1\n2");

        game.playTurn();
        game.playTurn();

        assertTrue(ui.notifiedOfInvalidInput());
    }
}
