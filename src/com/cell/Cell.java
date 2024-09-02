package com.cell;

import com.utility.Offset;

import javax.swing.*;
import java.awt.*;

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
    }

    Dimension getDimension() {
        return dimension;
    }
}
