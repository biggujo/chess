package com.view.panels.initializers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmptyPanelInitializer extends PanelInitializerImpl {
    public EmptyPanelInitializer(Dimension cellSize, Dimension cellsAmount) {
        super(cellSize, cellsAmount);
    }

    @Override
    public List<JComponent> initialize(List<JComponent> initialComponents) {
        return initialComponents;
    }
}
