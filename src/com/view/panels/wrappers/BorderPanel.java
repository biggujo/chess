package com.view.panels.wrappers;

import javax.swing.*;
import java.awt.*;

public class BorderPanel extends JPanel {
    public BorderPanel(JPanel otherPanel) {
        add(otherPanel);

        applyBorder();
    }

    protected void applyBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
