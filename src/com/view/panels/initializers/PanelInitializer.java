package com.view.panels.initializers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface PanelInitializer {
    List<JComponent> initialize(List<JComponent> initialComponents);

    Dimension getCellsAmount();
}
