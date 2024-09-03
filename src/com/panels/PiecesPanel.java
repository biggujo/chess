package com.panels;

import com.controller.FieldController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesPanel extends GridPanel {
    public PiecesPanel(Dimension cellsAmount, int cellSize) {
        super(cellsAmount, cellSize);

        addMouseListener(new PiecesMouseListener());
    }

    @Override
    List<JComponent> createCellField() {
        return FieldController.getInstance().getField();
    }
}
