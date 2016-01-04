package kg.ui.GUI;

import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JFXGamePlayedTest {
    private GraphicalUI ui;
    private Scene scene;
    @Before
    public void setUp() throws Exception {
        new JFXPanel();
        scene = new Scene(new Pane());
        ui = new GraphicalUI(new JFXVisualiser(scene));
    }

    @Test
    public void playsGameTillOver() {
        ui.displayGameSelector();

        click("HUMAN_ONLY", scene);
        click("0", scene);
        click("4", scene);
        click("3", scene);
        click("6", scene);
        click("2", scene);
        click("1", scene);
        click("7", scene);
        click("5", scene);
        click("8", scene);

        assertTrue(ui.getGame().isOver());
    }

    private void click(String id, Scene scene) {
        Node cell = scene.lookup("#" + id);
        cell.fireEvent(new Event(MouseEvent.MOUSE_CLICKED));
    }
}
