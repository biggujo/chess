package com.view.panels;

import java.awt.*;
import java.io.IOException;

abstract class GridPanel extends FieldPanel {
    public GridPanel(Dimension cellsAmount, int cellSize) throws IOException {
        super(cellsAmount, cellSize);

        applyGridLayout();
    }

    private void applyGridLayout() {
        setLayout(new GridLayout(getCellsAmount().width, getCellsAmount().height));
    }
}
