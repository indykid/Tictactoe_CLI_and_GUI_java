package kg.ui.GUI.SwingViewComponents;

import kg.jarkyn.Mark;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwingCellWidgetTest {
    @Test
    public void knowsItsPosition() {
        SwingCellWidget cell = new SwingCellWidget(1, Mark.NONE);

        assertEquals(1, cell.getPosition());
    }

    @Test
    public void noTextWhenMarkIsNone() {
        SwingCellWidget cell = new SwingCellWidget(0, Mark.NONE);

        assertEquals("", cell.getText());
    }

    @Test
    public void correctTextForMarkX() {
        SwingCellWidget cell = new SwingCellWidget(0, Mark.X);

        assertEquals("X", cell.getText());
    }

    @Test
    public void correctTextForMarkO() {
        SwingCellWidget cell = new SwingCellWidget(0, Mark.O);

        assertEquals("O", cell.getText());
    }
}
