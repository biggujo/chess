package com.view;

import com.globals.Defaults;
import com.view.panels.AvailableMovesPanel;
import com.view.panels.PiecesPanel;
import com.view.panels.SettingsPanel;
import com.view.panels.initializers.EmptyPanelInitializer;
import com.view.panels.initializers.PanelInitializer;

import java.awt.*;

public class ViewManager {
    public static ViewManager viewManager;
    private final AvailableMovesPanel availableMovesPanel;
    private final PiecesPanel piecesPanel;
    private final SettingsPanel settingsPanel;

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

        settingsPanel = new SettingsPanel();
    }

    public AvailableMovesPanel getAvailableMovesPanel() {
        return availableMovesPanel;
    }

    public PiecesPanel getPiecesPanel() {
        return piecesPanel;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }
}
