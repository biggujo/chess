package com.view;

import com.globals.Defaults;
import com.view.panels.AvailableMovesPanel;
import com.view.panels.FieldSerializationPanel;
import com.view.panels.PiecesPanel;
import com.view.panels.initializers.EmptyPanelInitializer;
import com.view.panels.initializers.PanelInitializer;

import java.awt.*;
import java.io.IOException;

public class ViewManager {
    public static ViewManager viewManager;
    private final AvailableMovesPanel availableMovesPanel;
    private final PiecesPanel piecesPanel;
    private final FieldSerializationPanel fieldSerializationPanel;

    public static ViewManager getInstance() {
        if (ViewManager.viewManager == null) {
            ViewManager.viewManager = new ViewManager();
        }

        return viewManager;
    }

    private ViewManager() {
        availableMovesPanel = new AvailableMovesPanel();

        Dimension cellSize = new Dimension(Defaults.PIECE_SIZE, Defaults.PIECE_SIZE);
        Dimension cellAmount = new Dimension(Defaults.TILE_AMOUNT, Defaults.TILE_AMOUNT);
        PanelInitializer emptyInitializer = new EmptyPanelInitializer(cellSize, cellAmount);

        piecesPanel = new PiecesPanel(emptyInitializer);

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
