package com.cell;

import com.utility.Offset;

import java.awt.*;

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
    }
}
