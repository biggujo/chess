package com.view.frames.promotion;

import com.controller.PiecesFieldController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnableFieldAdapter extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        PiecesFieldController.enableField();
    }
}
