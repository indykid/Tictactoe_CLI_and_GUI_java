package kg.jarkyn.GUI.ViewComponents;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import kg.jarkyn.Core.Mark;

public class GridCell extends StackPane {
    private final int position;
    private Text text = new Text();

    public GridCell(int position, Mark mark) {
        this.position = position;
        style();
        setupText(mark);
    }

    private void style() {
        setMinSize(200, 200);
        setStyle("-fx-border-color: gray");
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
        styleText();
        getChildren().add(text);
    }

    private void styleText() {
        text.setFill(Color.GREY);
        text.setStroke(Color.GREY);
        text.setFont(Font.font(80));
    }


}
