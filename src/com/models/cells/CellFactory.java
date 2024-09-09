package com.models.cells;

public final class CellFactory {
    private final BlackCellImpl blackCell;
    private final WhiteCellImpl whiteCell;

    public CellFactory() {
        this.blackCell = new BlackCellImpl();
        this.whiteCell = new WhiteCellImpl();
    }

    public Cell getInstance(CellType type) {
        return switch (type) {
            case BLACK -> blackCell;
            case WHITE -> whiteCell;
        };
    }
}
