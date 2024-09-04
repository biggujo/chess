package com.view.panels;

import java.awt.*;

abstract class GridPanel extends FieldPanel {
    public GridPanel(Dimension cellsAmount, int cellSize) {
        super(cellsAmount, cellSize);

        applyGridLayout();
    }

    private void applyGridLayout() {
        setLayout(new GridLayout(getCellsAmount().width, getCellsAmount().height));
    }
}
