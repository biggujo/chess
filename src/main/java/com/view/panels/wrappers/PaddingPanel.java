package com.view.panels.wrappers;

import javax.swing.*;
import javax.swing.border.Border;

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
