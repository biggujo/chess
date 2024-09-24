package com.view.panels.wrappers;

import com.globals.Defaults;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class PaddingPanel extends JPanel {
    private static final int PADDING_SIZE = 12;

    public PaddingPanel(JPanel otherPanel) {
        add(otherPanel);
        applyBorder();
    }

    protected void applyBorder() {
        Border emptyBorder = BorderFactory.createEmptyBorder(
                PADDING_SIZE,
                PADDING_SIZE,
                PADDING_SIZE,
                PADDING_SIZE);

        setBorder(emptyBorder);
    }
}
