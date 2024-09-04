package com.view.panels;

import com.controller.FieldController;
import com.globals.Defaults;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesPanel extends GridPanel {
    private static PiecesPanel instance;

    public static PiecesPanel getInstance() {
        if (PiecesPanel.instance == null) {
            PiecesPanel.instance = new PiecesPanel();
        }

        return PiecesPanel.instance;
    }

    private PiecesPanel() {
        super(new Dimension(Defaults.TILE_AMOUNT, Defaults.TILE_AMOUNT), Defaults.PIECE_SIZE);

        addMouseListener(new PiecesMouseListener());
    }

    @Override
    List<JComponent> createCellField() {
        return FieldController.getInstance().getField();
    }

    public void updateCells(List<JComponent> newCells) {
        removeAll();
        addCells(newCells);
        revalidate();
        repaint();
    }
}
