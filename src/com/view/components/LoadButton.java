package com.view.components;

import com.services.listeners.LoadFieldMouseListener;

import javax.swing.*;

public class LoadButton extends JButton {
    public LoadButton() {
        setText("Load the field");
        addMouseListener(new LoadFieldMouseListener());
    }
}
