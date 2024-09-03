package com.pieces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PieceComponent extends JComponent {
    private Dimension dimension;

    public PieceComponent(Dimension dimension) {
        this.dimension = dimension;

        setPreferredSize(dimension);

        Color c = new Color(1f, 0f, 0f, .5f);

        setBackground(c);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Image pawn = null;
        try {
            pawn = ImageIO.read(new File("assets/pawn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2d.drawImage(pawn, 0, 0, dimension.width, dimension.height, null);
    }
}
