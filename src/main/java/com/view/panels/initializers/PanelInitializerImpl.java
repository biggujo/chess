package com.view.panels.initializers;

import java.awt.*;

abstract public class PanelInitializerImpl implements PanelInitializer {
    private final Dimension cellSize;
    private final Dimension cellsAmount;

    public PanelInitializerImpl(Dimension cellSize, Dimension cellsAmount) {
        this.cellSize = cellSize;
        this.cellsAmount = cellsAmount;
    }

    protected Dimension getCellSize() {
        return cellSize;
    }

    @Override
    public Dimension getCellsAmount() {
        return cellsAmount;
    }
}
