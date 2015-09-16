package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class GameTest {
    private Player playerX;
    private Player playerO;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        playerX = new Player("x");
        playerO = new Player("o");
        output = new ByteArrayOutputStream();
    }

    @Test
    public void knowsItIsNotOverAtTheStart() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("irrelevant".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        assertFalse(game.isOver());
    }

    @Test
    public void knowsItIsOverWhenDrawn() {
        String[] moves = {"x", "o", "x",
                          "x", "x", "o",
                          "o", "x", "o"};
        Board board = new Board(moves);
        ByteArrayInputStream input = new ByteArrayInputStream("irrelevant".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        assertTrue(game.isOver());
    }

    @Test
    public void knowsItIsOverWhenWon() {
        String[] moves = {"x", "o", "x",
                          "x", "x", "o",
                          "o", "o", "x"};
        Board board = new Board(moves);
        ByteArrayInputStream input = new ByteArrayInputStream("irrelevant".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        assertTrue(game.isOver());
    }

    @Test
    public void displaysBoard() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();

        assertTrue(output.toString().contains("  1 |  2 |  3 \n" +
                                              "--------------\n" +
                                              "  4 |  5 |  6 \n" +
                                              "--------------\n" +
                                              "  7 |  8 |  9 \n"));
    }

    @Test
    public void addsMoveToTheBoard() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();

        assertEquals("x", game.getBoard().markAt(0));
    }

    @Test
    public void swapsPlayers() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();
        game.playTurn();

        assertNotEquals(game.getBoard().markAt(0), game.getBoard().markAt(1));
    }

    @Test
    public void playsTillWon() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n4\n2\n5\n3".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.play();

        assertTrue(game.isOver());
    }

    @Test
    public void playsTillDrawn() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n3\n5\n4\n6\n8\n7\n9".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.play();

        assertTrue(game.isOver());
    }

    @Test
    public void greets() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n4\n2\n5\n3".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.play();

        assertTrue(output.toString().contains("Welcome to Ticatactoe!\n"             +
                                              "This version requires two players.\n" +
                                              "First player is assigned mark X,\n"   +
                                              "second player is assigned mark O\n"));
    }

    @Test
    public void doesNotPlayIntoOccupiedPosition() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\n2".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();
        game.playTurn();

        assertEquals("x", game.getBoard().markAt(0));
        assertEquals("o", game.getBoard().markAt(1));
    }

    @Test
    public void doesNotPlayIntoNonExistingPosition() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("10\n1\n2".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();
        game.playTurn();

        assertEquals("x", game.getBoard().markAt(0));
        assertEquals("o", game.getBoard().markAt(1));
    }

    @Test
    public void doesNotPlayIntoInvalidPosition() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("a\n1\n2".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();
        game.playTurn();

        assertEquals("x", game.getBoard().markAt(0));
        assertEquals("o", game.getBoard().markAt(1));
    }

    @Test
    public void notifiesOfInvalidInputForNonNumericMove() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("a\n5".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }

    @Test
    public void notifiesOfInvalidMove() {
        Board board = new Board();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\n2".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Game game = new Game(board, playerX, playerO, ui);

        game.playTurn();
        game.playTurn();

        System.out.println(output.toString());

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }
}
