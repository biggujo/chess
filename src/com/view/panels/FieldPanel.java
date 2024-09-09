package com.view.panels;

import com.view.panels.initializers.PanelInitializer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class FieldPanel extends JPanel {
    private final PanelInitializer panelInitializer;

    public FieldPanel(PanelInitializer panelInitializer) {
        this.panelInitializer = panelInitializer;

        List<JComponent> components = panelInitializer.initialize(new ArrayList<>());
        addCells(components);
    }

    protected void addCells(List<JComponent> cells) {
        for (JComponent o : cells) {
            add(o);
        }
    }

    public Dimension getCellsAmount() {
        return panelInitializer.getCellsAmount();
    }
}
