package kg.jarkyn;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void hasMark() {
        Player playerX = new Player(Mark.X);

        assertEquals(Mark.X, playerX.mark);
    }
}
