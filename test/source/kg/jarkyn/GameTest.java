package kg.jarkyn;

import org.junit.Ignore;
import org.junit.Test;
import kg.jarkyn.doubles.UiDouble;

import static org.junit.Assert.*;
import static kg.jarkyn.Mark.*;

public class GameTest {
    private UiDouble ui;
    private final int[] irrelevantInput = new int[]{0};

    private Game setupGame(Board board, int[] inputs) {
        ui = new UiDouble(inputs);
        return new Game(board, new HumanPlayer(X, ui), new HumanPlayer(O, ui), ui);
    }

    @Test
    public void itIsNotOverAtTheStart() {
        Game game = setupGame(new Board(), irrelevantInput);

        assertFalse(game.isOver());
    }

    @Test
    public void itIsOverWhenDrawn() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, X, O};
        Game game = setupGame(new Board(moves), irrelevantInput);

        assertTrue(game.isOver());
    }

    @Test
    public void itIsOverWhenWon() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game = setupGame(new Board(moves), irrelevantInput);

        assertTrue(game.isOver());
    }

    @Test
    public void displaysBoard() {
        Game game = setupGame(new Board(), new int[]{1});

        game.playTurn();

        assertTrue(ui.boardWasDisplayed());
    }

    @Test
    public void addsMoveToTheBoard() {
        Game game = setupGame(new Board(), new int[]{1});

        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
    }

    @Test
    public void swapsPlayers() {
        Game game = setupGame(new Board(), new int[]{1, 2});

        game.playTurn();
        game.playTurn();

        assertNotEquals(game.getBoard().markAt(0), game.getBoard().markAt(1));
    }

    @Test
    public void playsTillWon() {
        Game game = setupGame(new Board(), new int[]{1, 4, 2, 5, 3});

        game.play();

        assertTrue(game.isOver());
    }

    @Test
    public void playsTillDrawn() {
        Game game = setupGame(new Board(), new int[]{1, 2, 3, 5, 4, 6, 8, 7, 9});

        game.play();

        assertTrue(game.isOver());
    }

    @Test
    public void doesNotPlayIntoOccupiedPosition() {
        Game game = setupGame(new Board(), new int[]{1, 1, 2});

        game.playTurn();
        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
        assertEquals(O, game.getBoard().markAt(1));
    }

    @Test
    public void doesNotPlayIntoNonExistingPosition() {
        Game game = setupGame(new Board(), new int[]{10, 1, 2}) ;

        game.playTurn();
        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
        assertEquals(O, game.getBoard().markAt(1));
    }

    @Test
    public void notifiesOfInvalidMoveIntoOccupiedPosition() {
        Game game = setupGame(new Board(), new int[]{1, 1, 2});

        game.playTurn();
        game.playTurn();

        assertTrue(ui.notifiedOfInvalidInput());
    }

    @Test
    public void announcesGameOverAtTheEnd() {
        Game game = setupGame(new Board(), new int[]{1, 2, 3, 5, 4, 6, 8, 7, 9});

        game.play();

        assertTrue(ui.gameOverAnnounced());
    }

    @Test
    public void announcesWinner() {
        Game game = setupGame(new Board(), new int[]{1, 4, 2, 5, 3});

        game.play();

        assertTrue(ui.winnerAnnounced());
    }

    @Test
    public void announcesDraw() {
        Game game = setupGame(new Board(), new int[]{1, 2, 3, 5, 4, 6, 8, 7, 9});

        game.play();

        assertTrue(ui.drawAnnounced());
    }

    @Test
    public void doesNotAnnouncesWinnerWhenDrawn() {
        Game game = setupGame(new Board(), new int[]{1, 2, 3, 5, 4, 6, 8, 7, 9});

        game.play();

        assertFalse(ui.winnerAnnounced());
    }

    @Test
    public void doesNotAnnounceDrawWenWinnerIsPresent() {
        Game game = setupGame(new Board(), new int[]{1, 4, 2, 5, 3});

        game.play();

        assertFalse(ui.drawAnnounced());
    }
}