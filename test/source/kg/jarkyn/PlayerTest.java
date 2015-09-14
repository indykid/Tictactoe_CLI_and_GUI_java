package kg.jarkyn;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jarkyn on 14/09/2015.
 */
public class PlayerTest {
    @Test
    public void hasMark() {
        Player playerX = new Player("x");

        assertEquals("x", playerX.mark);
    }
}
