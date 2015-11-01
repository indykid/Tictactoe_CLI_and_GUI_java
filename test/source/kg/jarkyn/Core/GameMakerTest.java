package kg.jarkyn.Core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameMakerTest {

    @Test
    public void givenAiFirstOptionAiIsPlayerXHumanIsPlayerO() {
        GameMaker.makeGame(GameOption.AI_FIRST, null);

        assertTrue(GameMaker.getPlayerX() instanceof AiPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenAiSecondOptionHumanAsPlayerXAiAsPlayerO() {
        GameMaker.makeGame(GameOption.AI_SECOND, null);

        assertTrue(GameMaker.getPlayerX() instanceof HumanPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof AiPlayer);
    }

    @Test
    public void givenAiSecondOptionOnlyHumanPlayers() {
        GameMaker.makeGame(GameOption.HUMAN_ONLY, null);

        assertTrue(GameMaker.getPlayerX() instanceof HumanPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof HumanPlayer);
    }
}