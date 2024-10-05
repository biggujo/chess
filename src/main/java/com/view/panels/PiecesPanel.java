package com.view.panels;

import com.services.guilisteners.PiecesMouseListener;
import com.view.panels.initializers.PanelInitializer;

import javax.swing.*;
import java.util.List;

public class PiecesPanel extends GridPanel {
    public PiecesPanel(PanelInitializer panelInitializer) {
        super(panelInitializer);

        addMouseListener(new PiecesMouseListener());
        setOpaque(false);
    }

    public void updateWith(List<JComponent> components) {
        removeAll();

        addCells(components);
    }
}
