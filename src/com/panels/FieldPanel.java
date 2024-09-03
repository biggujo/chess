package com.panels;

import javax.swing.*;
import java.awt.*;
import java.util.List;

abstract class FieldPanel extends JPanel {
    private final Dimension cellsAmount;
    private final int cellSize;

    public FieldPanel(Dimension cellsAmount, int cellSize) {
        this.cellsAmount = cellsAmount;
        this.cellSize = cellSize;

        List<JComponent> cells = createCellField();

        addCells(cells);
        setOpaque(false);
    }

    abstract List<JComponent> createCellField();

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
