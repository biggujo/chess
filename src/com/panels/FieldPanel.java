package com.panels;

import com.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.List;

abstract class FieldPanel extends JPanel {
    private final Dimension cellsAmount;
    private final int cellSize;

    public FieldPanel(Dimension cellsAmount, int cellSize) {
        this.cellsAmount = cellsAmount;
        this.cellSize = cellSize;

        addCells();
        setOpaque(false);
    }

    abstract List<JComponent> createCellField();

    private void addCells() {
        List<JComponent> cellList = createCellField();

        for (JComponent o : cellList) {
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
