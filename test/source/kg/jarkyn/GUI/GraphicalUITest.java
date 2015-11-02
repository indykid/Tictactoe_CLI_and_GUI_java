package kg.jarkyn.GUI;

import kg.jarkyn.Core.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphicalUITest {

    private GraphicalUI ui;
    private Visualiser visualiser;

    @Before
    public void setUp() throws Exception {
        visualiser = new VisualiserDummy();
        ui = new GraphicalUI(visualiser);
    }

    @Test
    public void setsUpGame() {
        ui.setupGame(GameOption.AI_FIRST);

        assertNotNull(ui.getGame());
    }

    @Test
    public void displaysGameSelector() {
        VisualiserSpy visualiser = new VisualiserSpy();
        ui = new GraphicalUI(visualiser);

        ui.displayGameSelector();

        assertTrue(visualiser.gameSelectionDisplayed);
    }

    @Test
    public void displaysBoard() {
        VisualiserSpy visualiser = new VisualiserSpy();
        ui = new GraphicalUI(visualiser);
        ui.setGame(makeGame());

        ui.displayBoard();

        assertTrue(visualiser.boardDisplayed);
    }

    @Test
    public void humanTurnIsNotPlayedWhenNoHumanMove() {
        ui.setGame(makeGame());

        ui.playGame();

        assertTrue(boardIsEmpty());
    }

    @Test
    public void humanTurnIsPlayedWhenHumanMoveIsSet() {
        Game game = makeGame();
        ui.setGame(game);
        ui.setHumanMove(1);

        ui.playGame();

        assertEquals(Mark.X, game.getBoard().markAt(1));
    }

    @Test
    public void aiTurnIsPlayed() {
        Game game = new Game(new Board(), new AiPlayer(Mark.X), new HumanPlayer(Mark.O, ui));
        ui.setGame(game);

        ui.playGame();

        assertFalse(boardIsEmpty());
    }

    @Test
    public void boardIsDisplayedAfterHumanMove() {
        VisualiserSpy visualiser = new VisualiserSpy();
        ui = new GraphicalUI(visualiser);
        Game game = makeGame();
        ui.setGame(game);
        ui.setHumanMove(1);

        ui.playGame();

        assertEquals(1, visualiser.boardDisplayCounter);
    }

    private boolean boardIsEmpty() {
        return ui.getGame().getBoard().isEmpty();
    }

    private Game makeGame() {
        return new Game(new Board(), new HumanPlayer(Mark.X, ui), new HumanPlayer(Mark.O, ui));
    }

    private class VisualiserDummy implements Visualiser {

        @Override
        public void displayGameSelectionWidget(GameOptionListener listener) {
        }

        @Override
        public void displayBoardWidget(Board board, PositionListener listener) {
        }
    }

    private class VisualiserSpy implements Visualiser {

        public boolean gameSelectionDisplayed = false;
        public boolean boardDisplayed = false;
        public int boardDisplayCounter = 0;

        @Override
        public void displayGameSelectionWidget(GameOptionListener listener) {
            gameSelectionDisplayed = true;
        }

        @Override
        public void displayBoardWidget(Board board, PositionListener listener) {
            boardDisplayCounter++;
            boardDisplayed = true;
        }
    }
}