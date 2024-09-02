package com.cell;

import com.utility.pair.Offset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

abstract class ColoredCell extends Cell {
    private final Color color;

    public ColoredCell(Offset offset, Dimension dimension, Color color) {
        super(offset, dimension);
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, getDimension().width, getDimension().height);

        try {
            Image image = ImageIO.read(new File("assets/pawn.png"));
            graphics2D.drawImage(
                    image,
                    getOffset().getX(),
                    getOffset().getY(),
                    getDimension().width,
                    getDimension().height,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graphics2D.dispose();
    }
}
