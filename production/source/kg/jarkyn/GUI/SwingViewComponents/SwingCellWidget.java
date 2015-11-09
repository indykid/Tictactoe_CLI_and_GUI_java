package kg.jarkyn.GUI.SwingViewComponents;

import kg.jarkyn.Core.Mark;

import javax.swing.*;
import java.awt.*;

public class SwingCellWidget extends JButton {
    private int position;

    public SwingCellWidget(int position, Mark mark) {
        setupText(mark);
        this.position = position;
        style();
    }

    private void style() {
        setPreferredSize(new Dimension(200, 200));
        setMinimumSize(new Dimension(200, 200));
        setFont(new Font("Arial", Font.PLAIN, 70));
    }

    public int getPosition() {
        return position;
    }

    private void setupText(Mark mark) {
        if (mark != Mark.NONE) {
            setText(mark.toString());
        }
    }
}
