package com.view.panels;

import com.view.panels.initializers.CellPanelInitializer;
import com.view.panels.initializers.PanelInitializer;

import java.awt.*;

final public class CellPanel extends GridPanel {

    public CellPanel(int pieceSize, int tileAmount) {
        super(CellPanel.createInitializer(pieceSize, tileAmount));
    }

    private static PanelInitializer createInitializer(int pieceSize, int tileAmount) {
        Dimension cellSize = new Dimension(pieceSize, pieceSize);
        Dimension cellAmount = new Dimension(tileAmount, tileAmount);
        return new CellPanelInitializer(cellSize, cellAmount);
    }
}
