package com.cell;

import java.awt.*;

abstract class ColoredCell extends Cell {
    public ColoredCell(Dimension dimension, Color color) {
        super(dimension);

        setBackground(color);
    }
}
