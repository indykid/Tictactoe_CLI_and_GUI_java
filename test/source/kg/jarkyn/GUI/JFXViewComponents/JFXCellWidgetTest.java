package kg.jarkyn.GUI.JFXViewComponents;

import kg.jarkyn.Core.Mark;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JFXCellWidgetTest {
    @Test
    public void noTextWhenMarkIsNone(){
        JFXCellWidget cell = new JFXCellWidget(1, Mark.NONE);

        assertEquals("", cell.getText());
    }

    @Test
    public void setsUpTextForMarkX() {
        JFXCellWidget cell = new JFXCellWidget(1, Mark.X);

        assertEquals("X", cell.getText());
    }

    @Test
    public void addsText() {
        JFXCellWidget cell = new JFXCellWidget(1, Mark.X);

        assertEquals(1, cell.getChildren().size());
    }

}