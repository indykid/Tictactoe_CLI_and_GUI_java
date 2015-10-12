package kg.jarkyn;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameSelectorTest {
    @Test
    public void returnsAiFirstGame() {
        Game game = GameSelector.makeGame(GameOption.AI_FIRST);

        assertTrue(game instanceof AiFirstGame);
    }

    @Test
    public void returnsAiSecondGame() {
        Game game = GameSelector.makeGame(GameOption.AI_SECOND);

        assertTrue(game instanceof AiSecondGame);
    }

    @Test
    public void givenOptionThreeSetsHumanOnlyPlayers() {
        Game game = GameSelector.makeGame(GameOption.HUMAN_ONLY);

        assertTrue(game instanceof HumanOnlyGame);
    }
}