package com.view;

import com.globals.Defaults;
import com.models.pieces.PlayerType;
import com.view.panels.AvailableMovesPanel;
import com.view.panels.captures.CapturesPanel;
import com.view.panels.captures.CapturesPanelImpl;
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
    private final CapturesPanel firstCapturesPanel;
    private final CapturesPanel secondCapturesPanel;

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

        firstCapturesPanel = new CapturesPanelImpl();
        secondCapturesPanel = new CapturesPanelImpl();
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

    public CapturesPanel getCapturesPanelBy(PlayerType playerType) {
        return switch (playerType) {
            case FIRST -> secondCapturesPanel;
            case SECOND -> firstCapturesPanel;
            default -> null;
        };
    }

    public CapturesPanel getOpponentCapturesPanelBy(PlayerType playerType) {
        return switch (playerType) {
            case FIRST -> firstCapturesPanel;
            case SECOND -> secondCapturesPanel;
            default -> null;
        };
    }
}
