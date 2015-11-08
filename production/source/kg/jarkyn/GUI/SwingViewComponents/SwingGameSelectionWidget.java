package kg.jarkyn.GUI.SwingViewComponents;

import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.GameOptionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGameSelectionWidget extends JPanel {
    public SwingGameSelectionWidget(GameOptionListener listener) {
        setupButtons(listener);
    }

    private void setupButtons(GameOptionListener listener) {
        for (GameOption gameOption : GameOption.values()) {
            SwingGameOptionButton button = new SwingGameOptionButton(gameOption);
            addListener(listener, button);
            add(button);
        }
    }

    private void addListener(final GameOptionListener listener, SwingGameOptionButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.gameOptionSelected(button.getGameOption());
            }
        });
    }
}
