package com.view.labels;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoLabel extends JLabel {
    private final String playerName;

    public PlayerInfoLabel(String playerName) {
        this.playerName = playerName;
        setPreferredSize(new Dimension(10, 50));
        setInactive();
    }

    public void setActive() {
        setText("Player: " + playerName + " (CURRENT)");
    }

    public void setInactive() {
        setText("Player: " + playerName);
    }
}
