package com.view.panels;

import com.view.panels.initializers.PanelInitializer;

import java.awt.*;

public abstract class GridPanel extends FieldPanel {
    public GridPanel(PanelInitializer panelInitializer) {
        super(panelInitializer);

        applyGridLayout();
    }

    private void applyGridLayout() {
        setLayout(new GridLayout(getCellsAmount().width, getCellsAmount().height));
    }
}
