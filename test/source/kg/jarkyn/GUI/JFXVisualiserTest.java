package kg.jarkyn.GUI;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import kg.jarkyn.Core.Board;
import kg.jarkyn.GUI.JFXViewComponents.JFXBoardWidget;
import kg.jarkyn.doubles.PositionListenerDummy;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JFXVisualiserTest {
    @Test
    public void makesBoardWidget() {
        JFXVisualiser visualiser = new JFXVisualiser(new Scene(new Pane()));

        assertTrue(visualiser.makeBoardWidget(new Board(), null) instanceof JFXBoardWidget);
    }

    @Test
    public void assignsPositionListenersToCells() {
        JFXVisualiser visualiser = new JFXVisualiser(new Scene(new Pane()));

        JFXBoardWidget boardWidget = visualiser.makeBoardWidget(new Board(), new PositionListenerDummy());

        assertTrue(clickListenersAreSet(boardWidget));
    }

    @Test
    public void showsBoardWidget() {
        JFXVisualiser visualiser = new JFXVisualiser(new Scene(new Pane()));

        visualiser.displayBoardWidget(new Board(), null);

        assertTrue(boardIsVisible(visualiser));
    }

    private boolean clickListenersAreSet(JFXBoardWidget boardWidget) {
        for (Node cell : boardWidget.getChildren()) {
            if (cell.onMouseClickedProperty().getValue() == null) {
                return false;
            }
        }
        return true;
    }

    private boolean boardIsVisible(JFXVisualiser visualiser) {
        return visualiser.getScene().getRoot() instanceof JFXBoardWidget;
    }
}
