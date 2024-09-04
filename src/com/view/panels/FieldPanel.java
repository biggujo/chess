package com.view.panels;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

abstract class FieldPanel extends JPanel {
    private final Dimension cellsAmount;
    private final int cellSize;

    public FieldPanel(Dimension cellsAmount, int cellSize) throws IOException {
        this.cellsAmount = cellsAmount;
        this.cellSize = cellSize;

        List<JComponent> cells = createCellField();

        addCells(cells);
        setOpaque(false);
    }

    abstract List<JComponent> createCellField() throws IOException;

    protected void addCells(List<JComponent> cells) {
        for (JComponent o : cells) {
            add(o);
        }
    }

    Dimension getCellsAmount() {
        return cellsAmount;
    }

    int getCellSize() {
        return cellSize;
    }
}
