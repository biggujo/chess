package com.cell;

import com.utility.pair.Offset;

import java.awt.*;

public final class CellFactory {
    private final Dimension cellDimension;

    public CellFactory(Dimension cellDimension) {
        this.cellDimension = cellDimension;
    }

    public Cell getInstance(Offset offset, CellType type) {
        return switch (type) {
            case BLACK -> new BlackCell(offset, cellDimension);
            case WHITE -> new WhiteCell(offset, cellDimension);
            default -> throw new UnsupportedOperationException();
        };
    }
}
