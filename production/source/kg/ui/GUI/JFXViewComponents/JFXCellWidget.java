package kg.ui.GUI.JFXViewComponents;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import kg.jarkyn.Mark;

public class JFXCellWidget extends StackPane {
    private final int position;
    private Text text = new Text();

    public JFXCellWidget(int position, Mark mark) {
        this.position = position;
        style();
        setupText(mark);
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text.getText();
    }

    private void style() {
        setMinSize(200, 200);
        setStyle("-fx-border-color: gray");
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
