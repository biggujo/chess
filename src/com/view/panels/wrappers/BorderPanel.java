package com.view.panels.wrappers;

import com.globals.Defaults;

import javax.swing.*;

public class BorderPanel extends JPanel {
    public BorderPanel(JPanel otherPanel) {
        add(otherPanel);

        applyBorder();
    }

    protected void applyBorder() {
        setBorder(BorderFactory.createLineBorder(Defaults.colorScheme.getDark()));
    }
}
