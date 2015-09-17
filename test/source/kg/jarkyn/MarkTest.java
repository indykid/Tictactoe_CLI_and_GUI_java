package kg.jarkyn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkTest {
    @Test
    public void opponentOfXIsO() {
        assertEquals(Mark.O, Mark.X.opponent());
    }

    @Test
    public void opponentOfOIsX() {
        assertEquals(Mark.X, Mark.O.opponent());
    }
}
