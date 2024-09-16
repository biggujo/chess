package com.view.panels;

import com.view.components.LoadButton;
import com.view.components.SaveButton;
import com.view.components.SwapColorsButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsPanel extends JPanel {
    private static final int Y_GAP = 16;
    private static final List<JComponent> components;

    static {
        components = new ArrayList<>();
        components.add(new SwapColorsButton());
        components.add(new SaveButton());
        components.add(new LoadButton());
    }

    public SettingsPanel() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        addToTheContainer(container, components);
        this.add(container);
    }

    private static void addToTheContainer(JPanel container, List<JComponent> components) {
        for (JComponent component : components) {
            container.add(component);
            container.add(Box.createRigidArea(new Dimension(0, Y_GAP)));

        }
    }
}
