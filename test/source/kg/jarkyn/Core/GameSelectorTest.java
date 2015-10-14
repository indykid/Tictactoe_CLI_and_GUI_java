package kg.jarkyn.Core;

import kg.jarkyn.Core.AiPlayer;
import kg.jarkyn.Core.GameOption;
import kg.jarkyn.Core.GameSelector;
import kg.jarkyn.Core.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameSelectorTest {

    @Before
    public void setup() {
    }

    @Test
    public void givenAiFirstOptionAiIsPlayerXHumanIsPlayerO() {
        GameSelector.makeGame(GameOption.AI_FIRST, null);

        assertTrue(GameSelector.getPlayerX() instanceof AiPlayer);
        assertTrue(GameSelector.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenAiSecondOptionHumanAsPlayerXAiAsPlayerO() {
        GameSelector.makeGame(GameOption.AI_SECOND, null);

        assertTrue(GameSelector.getPlayerX() instanceof HumanPlayer);
        assertTrue(GameSelector.getPlayerO() instanceof AiPlayer);
    }

    @Test
    public void givenAiSecondOptionOnlyHumanPlayers() {
        GameSelector.makeGame(GameOption.HUMAN_ONLY, null);

        assertTrue(GameSelector.getPlayerX() instanceof HumanPlayer);
        assertTrue(GameSelector.getPlayerO() instanceof HumanPlayer);
    }
}