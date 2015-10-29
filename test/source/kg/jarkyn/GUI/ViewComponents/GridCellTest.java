package kg.jarkyn.GUI.ViewComponents;

import kg.jarkyn.Core.Mark;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridCellTest {
    @Test
    public void noTextWhenMarkIsNone(){
        GridCell cell = new GridCell(1, Mark.NONE);

        assertEquals("", cell.getText());
    }

    @Test
    public void setsUpTextForMarkX() {
        GridCell cell = new GridCell(1, Mark.X);

        assertEquals("X", cell.getText());
    }

    @Test
    public void addsText() {
        GridCell cell = new GridCell(1, Mark.X);

        assertEquals(1, cell.getChildren().size());
    }
}
