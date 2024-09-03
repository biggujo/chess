package com.panels;

import com.pieces.PieceComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PiecesPanel extends GridPanel {
    public PiecesPanel(Dimension cellsAmount, int cellSize) {
        super(cellsAmount, cellSize);
    }

    @Override
    List<JComponent> createCellField() {
        List<JComponent> pieces = new ArrayList<>();

        for (int i = 0; i < getCellsAmount().width; i++) {
            for (int j = 0; j < getCellsAmount().height; j++) {
                pieces.add(new PieceComponent(new Dimension(50, 50)));
            }
        }

        return pieces;
    }
}
