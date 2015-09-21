package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.*;

public class GameTest {
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    private Game setupGame(Board board, String input) {
        Ui ui = new Ui(new Cli(inputStream(input), output));
        return new Game(board, new Player(X), new Player(O), ui);
    }

    private ByteArrayInputStream inputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @Test
    public void knowsItIsNotOverAtTheStart() {
        Game game = setupGame(new Board(), "irrelevant");

        assertFalse(game.isOver());
    }

    @Test
    public void knowsItIsOverWhenDrawn() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, X, O};
        Game game = setupGame(new Board(moves), "irrelevant");

        assertTrue(game.isOver());
    }

    @Test
    public void knowsItIsOverWhenWon() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game = setupGame(new Board(moves), "irrelevant");

        assertTrue(game.isOver());
    }

    @Test
    public void displaysBoard() {
        Game game = setupGame(new Board(), "1");

        game.playTurn();

        assertTrue(output.toString().contains("  1 |  2 |  3 \n" +
                                              "--------------\n" +
                                              "  4 |  5 |  6 \n" +
                                              "--------------\n" +
                                              "  7 |  8 |  9 \n"));
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
    public void greets() {
        Game game = setupGame(new Board(), ("1\n4\n2\n5\n3"));

        game.play();

        assertTrue(output.toString().contains("Welcome to Tictactoe!\n"));
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

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }

    @Test
    public void notifiesOfInvalidMove() {
        Game game = setupGame(new Board(), "1\n1\n2");

        game.playTurn();
        game.playTurn();

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }
}
