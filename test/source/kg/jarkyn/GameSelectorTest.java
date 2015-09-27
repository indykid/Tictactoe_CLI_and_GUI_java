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

        assertEquals(2, gameSelector.getGameType());
    }

    @Test
    public void givenOptionOneCreatesAiAsXPlayerAndHumanAsOPlayer() {
        UiDouble ui = new UiDouble("1");
        GameSelector gameSelector = new GameSelector(ui);

        gameSelector.makeGame();

        assertTrue(gameSelector.getPlayerX() instanceof AiPlayer);
        assertTrue(gameSelector.getPlayerO() instanceof HumanPlayer);
    }
}
