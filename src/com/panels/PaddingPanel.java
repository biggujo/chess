package com.panels;

import javax.swing.*;

public class PaddingPanel extends JPanel {
    private static final int PADDING_SIZE = 12;

    public PaddingPanel(JPanel otherPanel) {
        setBorder(BorderFactory.createEmptyBorder(
                PADDING_SIZE,
                PADDING_SIZE,
                PADDING_SIZE,
                PADDING_SIZE));
        add(otherPanel);
    }
}
