package com.models.cells;

import java.awt.*;

abstract class CellImpl implements Cell {
    private final Color color;

    public CellImpl(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
