package com.view.panels;

import com.view.components.LoadButton;
import com.view.components.SaveButton;

import javax.swing.*;

public class FieldSerializationPanel extends JPanel {
    public FieldSerializationPanel() {
        add(new SaveButton());
        add(new LoadButton());
    }
}
