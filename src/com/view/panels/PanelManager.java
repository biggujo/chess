package com.view.panels;

import com.controller.PiecesFieldController;
import com.globals.Defaults;
import com.view.panels.initializers.EmptyPanelInitializer;
import com.view.panels.initializers.PanelInitializer;

import java.awt.*;
import java.io.IOException;

public class PanelManager {
    public static PanelManager panelManager;
    private final AvailableMovesPanel availableMovesPanel;
    private final PiecesPanel piecesPanel;
    private final FieldSerializationPanel fieldSerializationPanel;

    public static PanelManager getInstance() throws IOException {
        if (PanelManager.panelManager == null) {
            PanelManager.panelManager = new PanelManager();
        }

        return panelManager;
    }

    private PanelManager() {
        availableMovesPanel = new AvailableMovesPanel();

        Dimension cellSize = new Dimension(Defaults.PIECE_SIZE, Defaults.PIECE_SIZE);
        Dimension cellAmount = new Dimension(Defaults.TILE_AMOUNT, Defaults.TILE_AMOUNT);
        PanelInitializer emptyInitializer = new EmptyPanelInitializer(cellSize, cellAmount);

        piecesPanel = new PiecesPanel(emptyInitializer);
        PiecesFieldController.updatePiecesPanel();

        fieldSerializationPanel = new FieldSerializationPanel();
    }

    public AvailableMovesPanel getAvailableMovesPanel() {
        return availableMovesPanel;
    }

    public PiecesPanel getPiecesPanel() {
        return piecesPanel;
    }

    public FieldSerializationPanel getFieldSerializationPanel() {
        return fieldSerializationPanel;
    }
}
