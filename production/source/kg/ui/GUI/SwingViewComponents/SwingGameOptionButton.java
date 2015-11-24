package kg.ui.GUI.SwingViewComponents;


import kg.jarkyn.GameOption;

import javax.swing.*;
import java.awt.*;

public class SwingGameOptionButton extends JButton {
    private final GameOption gameOption;

    public SwingGameOptionButton(GameOption gameOption) {
        this.gameOption = gameOption;
        style();
    }

    private void style() {
        setText(gameOption.readableOption());

//        setFont(new Font("Arial", Font.PLAIN, 20));// to set font size
        setPreferredSize(new Dimension(200, 200));
        setMinimumSize(new Dimension(200, 200));
        // set background colour and font colour
    }

    public GameOption getGameOption() {
        return gameOption;
    }
}
