package com.view.panels;

import java.io.IOException;

public class PanelManager {
    public static PanelManager panelManager;
    private final AvailableMovesPanel availableMovesPanel;
    private final PiecesPanel piecesPanel;

    public static PanelManager getInstance() throws IOException {
        if (PanelManager.panelManager == null) {
            PanelManager.panelManager = new PanelManager();
        }

        return panelManager;
    }

    public PanelManager() throws IOException {
        availableMovesPanel = new AvailableMovesPanel();
        piecesPanel = new PiecesPanel();
    }

    public AvailableMovesPanel getAvailableMovesPanel() {
        return availableMovesPanel;
    }

    public PiecesPanel getPiecesPanel() {
        return piecesPanel;
    }
}
