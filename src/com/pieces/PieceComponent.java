package com.pieces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PieceComponent extends JComponent {
    public PieceComponent(Dimension dimension) {
        setPreferredSize(dimension);

        setBackground(Color.LIGHT_GRAY);
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

        g2d.drawImage(pawn, 0, 0, null);
    }
}
