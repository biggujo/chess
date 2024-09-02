package com.field;

import com.cell.Cell;
import com.cell.CellFactory;
import com.cell.CellType;
import com.utility.Offset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FieldPanel extends JPanel {
    private final Dimension panelCellAmountDimension;
    private final int cellSize;

    public FieldPanel(Dimension panelCellAmountDimension, int cellSize) {
        this.panelCellAmountDimension = panelCellAmountDimension;
        this.cellSize = cellSize;

        applyDimension();
        applyLayout();
        applyBorder();

        addCells();
    }

    private void applyDimension() {
        Dimension preferredSizeDimension = new Dimension(panelCellAmountDimension.width * cellSize, panelCellAmountDimension.height * cellSize);
        setPreferredSize(preferredSizeDimension);
    }

    private void applyLayout() {
        setLayout(new GridLayout(panelCellAmountDimension.width, panelCellAmountDimension.height));
    }

    private void applyBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private List<Cell> createCellField() {
        List<Cell> fields = new ArrayList<>();

        Dimension cellDimension = new Dimension(cellSize, cellSize);
        CellFactory cellFactory = new CellFactory(cellDimension);

        for (int i = 0; i < panelCellAmountDimension.width; i++) {
            for (int j = 0; j < panelCellAmountDimension.height; j++) {
                Offset offset = getOffset(i, j);
                CellType cellType = getCellType(i, j);

                Cell cell = cellFactory.getInstance(offset, cellType);
                fields.add(cell);
            }
        }

        return fields;
    }

    private Offset getOffset(int row, int col) {
        int offsetX = row * cellSize;
        int offsetY = col * cellSize;

        return new Offset(offsetX, offsetY);
    }

    private CellType getCellType(int row, int col) {
        return switch (row * col % 2) {
            case 1 -> CellType.BLACK;
            case 0 -> CellType.WHITE;
            default -> throw new IllegalStateException("Unexpected value: " + row * col % 2);
        };
    }

    private void addCells() {
        List<Cell> cellList = createCellField();

        for (Cell o : cellList) {
            add(o);
        }
    }
}
