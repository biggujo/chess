package com.view.panels;

import com.globals.Defaults;
import com.model.piecesfieldmodel.PiecesFieldModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PiecesPanel extends GridPanel {
    private static PiecesPanel instance;

    public static PiecesPanel getInstance() throws IOException {
        if (PiecesPanel.instance == null) {
            PiecesPanel.instance = new PiecesPanel();
        }

        return PiecesPanel.instance;
    }

    private PiecesPanel() throws IOException {
        super(new Dimension(Defaults.TILE_AMOUNT, Defaults.TILE_AMOUNT), Defaults.PIECE_SIZE);

        addMouseListener(new PiecesMouseListener());
    }

    @Override
    List<JComponent> createCellField() {
        return PiecesFieldModel.getField();
    }

    public void updateWith(List<JComponent> newCells) {
        removeAll();
        addCells(newCells);
    }
}
