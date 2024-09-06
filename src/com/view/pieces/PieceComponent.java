package com.view.pieces;

import javax.swing.*;
import java.awt.*;

public abstract class PieceComponent extends JComponent {
    private final Dimension dimension;
    private final Image image;

    public PieceComponent(Dimension dimension, Image image) {
        this.dimension = dimension;
        this.image = image;

        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, 0, 0, dimension.width, dimension.height, null);
    }

    public void setActive() {
        setOpaque(true);
        setBackground(Color.YELLOW);
    }

    public void setInactive() {
        setOpaque(false);
        setBackground(null);
    }
}
