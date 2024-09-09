package com.view.panels.initializers;

import com.globals.Defaults;
import com.models.cells.Cell;
import com.models.cells.CellFactory;
import com.models.cells.CellType;
import com.view.components.CellComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellPanelInitializer extends PanelInitializerImpl {
    public CellPanelInitializer(Dimension cellSize, Dimension cellsAmount) {
        super(cellSize, cellsAmount);
    }

    @Override
    public List<JComponent> initialize(List<JComponent> initialComponents) {
        List<JComponent> fields = new ArrayList<>();

        CellFactory cellFactory = new CellFactory();

        for (int i = 0; i < getCellsAmount().width; i++) {
            for (int j = 0; j < getCellsAmount().height; j++) {
                CellType cellType = getCellType(i, j);

                Cell cell = cellFactory.getInstance(cellType);
                Dimension dimension = new Dimension(Defaults.PIECE_SIZE, Defaults.PIECE_SIZE);
                CellComponent cellComponent = new CellComponent(dimension, cell.getColor());

                fields.add(cellComponent);
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
