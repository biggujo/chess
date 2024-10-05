package com.services.guilisteners;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisposeFrameListener extends MouseAdapter {
    private final Window frame;

    public DisposeFrameListener(Window frame) {
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        frame.dispose();
    }
}
