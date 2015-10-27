package kg.jarkyn.GUI.ViewComponents;

import javafx.scene.shape.Rectangle;
import kg.jarkyn.Core.Mark;

public class GridCell extends Rectangle {
    private final int position;
    private Mark mark;

    public GridCell(int position) {
        this.position = position;
    }

    public Mark getMark() {
        return mark;
    }
}
