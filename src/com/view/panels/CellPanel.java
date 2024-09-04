package com.view.panels;

import com.view.cell.Cell;
import com.view.cell.CellFactory;
import com.view.cell.CellType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CellPanel extends GridPanel {
    public CellPanel(Dimension cellsAmount, int cellSize) throws IOException {
        super(cellsAmount, cellSize);
    }

    @Override
    List<JComponent> createCellField() {
        List<JComponent> fields = new ArrayList<>();

        Dimension cellDimension = new Dimension(getCellSize(), getCellSize());
        CellFactory cellFactory = new CellFactory(cellDimension);

        for (int i = 0; i < getCellsAmount().width; i++) {
            for (int j = 0; j < getCellsAmount().height; j++) {
                CellType cellType = getCellType(i, j);

                Cell cell = cellFactory.getInstance(cellType);
                fields.add(cell);
            }
        }

        return fields;
    }

    private CellType getCellType(int row, int col) {
        return switch ((row + col) % 2) {
            case 1 -> CellType.BLACK;
            case 0 -> CellType.WHITE;
            default -> throw new IllegalStateException("Unexpected value: " + row * col % 2);
        };
    }
}
