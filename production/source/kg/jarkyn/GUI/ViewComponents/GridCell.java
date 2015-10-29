package kg.jarkyn.GUI.ViewComponents;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import kg.jarkyn.Core.Mark;

public class GridCell extends StackPane {
    private final int position;
    private Text text = new Text();

    public GridCell(int position, Mark mark) {
        this.position = position;
        setupText(mark);
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text.getText();
    }

    private void setupText(Mark mark) {
        if (mark != Mark.NONE) {
            text.setText(mark.toString());
        }
        getChildren().add(text);
    }
}
