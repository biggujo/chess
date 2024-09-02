package com.cell;

import com.utility.pair.Offset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Cell extends JComponent {
    private final Offset offset;
    private final Dimension dimension;

    public Cell(Offset offset, Dimension dimension) {
        this.offset = offset;
        this.dimension = dimension;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawRect(
                offset.getX(),
                offset.getY(),
                dimension.width,
                dimension.height
        );

        Image image = Toolkit.getDefaultToolkit().getImage("pawn.png");

        graphics2D.drawImage(image,
                offset.getX(),
                offset.getY(),
                dimension.width,
                dimension.height,
                null);
    }

    Dimension getDimension() {
        return dimension;
    }
}
