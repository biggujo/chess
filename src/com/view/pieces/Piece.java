package com.view.pieces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Piece extends JComponent {
    private static final String BASE_PATH = "assets/";

    private final Dimension dimension;
    private final String iconName;

    public Piece(Dimension dimension, String iconName) {
        this.dimension = dimension;
        this.iconName = iconName;

        setPreferredSize(dimension);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Image pawn = null;
        try {
            String iconPath = BASE_PATH + iconName;

            pawn = ImageIO.read(new File(iconPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2d.drawImage(pawn, 0, 0, dimension.width, dimension.height, null);
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
