package kg.jarkyn.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.control.Control;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GraphicalUITest {
    @Test
    public void displaysGameSelector() {
        new JFXPanel();
        GraphicalUI ui = new GraphicalUI();

        ui.displayGameSelector();

        List<Node> controls = getControls(ui);
        assertEquals(3, controls.size());
        assertThat(controls.get(0), instanceOf(Control.class));
        assertThat(controls.get(1), instanceOf(Control.class));
        assertThat(controls.get(2), instanceOf(Control.class));
    }

    private List<Node> getControls(GraphicalUI ui) {
        return ui.getScene().getRoot().getChildrenUnmodifiable();
    }
}
