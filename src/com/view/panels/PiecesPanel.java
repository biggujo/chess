package com.view.panels;

import com.globals.Defaults;
import com.models.piecesfield.PiecesFieldModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PiecesPanel extends GridPanel {
    PiecesPanel() throws IOException {
        super(new Dimension(Defaults.TILE_AMOUNT, Defaults.TILE_AMOUNT), Defaults.PIECE_SIZE);

        addMouseListener(new PiecesMouseListener());
        setOpaque(false);
    }

    @Override
    List<JComponent> createCellField() {
        return PiecesFieldModel.getComponents();
    }

    public void updateWith(List<JComponent> newCells) {
        removeAll();
        addCells(newCells);
    }
}
