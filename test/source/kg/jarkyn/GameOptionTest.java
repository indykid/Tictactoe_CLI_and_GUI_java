package kg.jarkyn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOptionTest {
    @Test
    public void returnsReadableDescriptionForAIFirst() {
        assertEquals("1 - computer plays first", GameOption.AI_FIRST.readableOption());
    }

    @Test
    public void returnsReadableDescriptionForAIPlaysSecond() {
        assertEquals("2 - computer plays second", GameOption.AI_SECOND.readableOption());
    }

    @Test
    public void returnsReadableDescriptionForHumanOnlyGame() {
        assertEquals("3 - play against your friend (first to go plays X)", GameOption.HUMAN_ONLY.readableOption());
    }
}
