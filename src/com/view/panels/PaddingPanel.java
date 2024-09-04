package com.view.panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class PaddingPanel extends JPanel {
    private static final int PADDING_SIZE = 12;

    public PaddingPanel(JPanel otherPanel) {
        applyBorder();
        add(otherPanel);
    }

    private void applyBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border emptyBorder = BorderFactory.createEmptyBorder(
                PADDING_SIZE,
                PADDING_SIZE,
                PADDING_SIZE,
                PADDING_SIZE);

        CompoundBorder border = new CompoundBorder(emptyBorder, blackBorder);

        setBorder(border);
    }
}
