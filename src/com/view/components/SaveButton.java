package com.view.components;

import com.services.listeners.SaveFieldMouseListener;

import javax.swing.*;

public class SaveButton extends JButton {
    public SaveButton() {
        setText("Save the field");
        addMouseListener(new SaveFieldMouseListener());
    }
}
