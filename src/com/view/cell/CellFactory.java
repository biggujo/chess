package com.view.cell;

import java.awt.*;

public final class CellFactory {
    private final Dimension cellDimension;

    public CellFactory(Dimension cellDimension) {
        this.cellDimension = cellDimension;
    }

    public Cell getInstance(CellType type) {
        return switch (type) {
            case BLACK -> new BlackCell(cellDimension);
            case WHITE -> new WhiteCell(cellDimension);
        };
    }
}
