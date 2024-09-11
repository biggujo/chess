package com.services.listeners;

import com.controller.SerializationController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SaveFieldMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        SerializationController.serializeField();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
