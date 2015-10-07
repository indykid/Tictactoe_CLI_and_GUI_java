package kg.jarkyn;

import kg.jarkyn.doubles.UiDouble;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameSelectorTest {
    @Test
    public void greets() {
        UiDouble ui = new UiDouble("1");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertTrue(ui.greetingWasDisplayed());
    }

    @Test
    public void displaysGameOptions() {
        UiDouble ui = new UiDouble("1");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertTrue(ui.gameSelectionDisplayed());
    }

    @Test
    public void receivesGameType() {
        UiDouble ui = new UiDouble("2");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertEquals(GameOption.AI_SECOND.value, gameSelector.getGameType());
    }

    @Test
    public void givenOptionOneSetsAiAsXPlayerAndHumanAsOPlayer() {
        UiDouble ui = new UiDouble("1");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertTrue(gameSelector.getPlayerX() instanceof AiPlayer);
        assertTrue(gameSelector.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenOptionTwoSetsHumanAsXPlayerAndAiAsOPlayer() {
        UiDouble ui = new UiDouble("2");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertTrue(gameSelector.getPlayerX() instanceof HumanPlayer);
        assertTrue(gameSelector.getPlayerO() instanceof AiPlayer);
    }

    @Test
    public void givenOptionThreeSetsHumanOnlyPlayers() {
        UiDouble ui = new UiDouble("3");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertTrue(gameSelector.getPlayerX() instanceof HumanPlayer);
        assertTrue(gameSelector.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void returnsGame() {
        UiDouble ui = new UiDouble("3");
        GameSelector gameSelector = new GameSelector(ui);

        Game game = gameSelector.makeGame();

        assertTrue(game != null);
    }
}