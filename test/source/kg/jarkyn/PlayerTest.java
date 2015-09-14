package kg.jarkyn;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void hasMark() {
        Player playerX = new Player("x");

        assertEquals("x", playerX.mark);
    }
}
