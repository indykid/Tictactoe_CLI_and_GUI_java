package kg.jarkyn.Core;

import kg.jarkyn.doubles.UiDouble;
import org.junit.Before;
import org.junit.Test;

import static kg.jarkyn.Core.Mark.*;
import static org.junit.Assert.*;

public class GameTest {
    private Ui ui;

    private Game setupGame() {
        return new Game(new Board(), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));
    }

    @Before
    public void setup() {
        ui = new UiDouble(new int[]{});
    }

    @Test
    public void itIsNotOverAtTheStart() {
        Game game = setupGame();

        assertFalse(game.isOver());
    }

    @Test
    public void itIsOverWhenDrawn() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, X, O};
        Game game = new Game(new Board(moves), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));

        assertTrue(game.isOver());
    }

    @Test
    public void itIsOverWhenWon() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game;
        game = new Game(new Board(moves), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));

        assertTrue(game.isOver());
    }

    @Test
    public void noWinnerAtTheStart() {
        Game game = setupGame();

        assertEquals(NONE, game.winnerMark());
    }

    @Test
    public void knowsWinner() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game = new Game(new Board(moves), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));

        assertEquals(X, game.winnerMark());
    }

    @Test
    public void addsMoveToTheBoard() {
        ui = new UiDouble(new int[]{1});
        Game game = new Game(new Board(), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));

        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
    }

    @Test
    public void swapsPlayers() {
        ui = new UiDouble(new int[]{1, 2});
        Game game = new Game(new Board(), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));

        game.playTurn();
        game.playTurn();

        assertNotEquals(game.getBoard().markAt(0), game.getBoard().markAt(1));
    }

    @Test
    public void doesNotPlayIfCurrentPlayerHasNoMove() {
        Player playerX = new HumanPlayerWithoutMoveStub(Mark.X);
        Game game = new Game(new Board(), playerX, new HumanPlayer(Mark.O, ui));

        game.playTurn();
        Board board = game.getBoard();

        int size = board.getSize() * board.getSize();
        assertEquals(size, board.getAvailable().size());
    }

    @Test
    public void playsTurnIfCurrentPlayerHasMove() {
        Player playerX = new HumanPlayerWithMoveStub(Mark.X);
        Game game = new Game(new Board(), playerX, new HumanPlayer(Mark.O, ui));

        game.playTurn();
        Board board = game.getBoard();

        assertEquals(Mark.X, board.markAt(0));

    }

    private class HumanPlayerWithoutMoveStub extends Player {
        public HumanPlayerWithoutMoveStub(Mark mark) {
            super(mark);
        }

        @Override
        public int pickPosition(Board board) {
            return 0;
        }

        @Override
        public boolean hasNextMove() {
            return false;
        }
    }

    private class HumanPlayerWithMoveStub extends Player {
        public HumanPlayerWithMoveStub(Mark mark) {
            super(mark);
        }

        @Override
        public int pickPosition(Board board) {
            return 0;
        }

        @Override
        public boolean hasNextMove() {
            return true;
        }
    }
}