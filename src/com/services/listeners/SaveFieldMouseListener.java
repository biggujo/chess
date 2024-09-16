package com.services.listeners;

import com.controller.SerializationController;
import com.view.choosers.ChessFileChooser;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class SaveFieldMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ChessFileChooser fileChooser = new ChessFileChooser();
        try {
            File file = fileChooser.save();
            SerializationController.serializeField(file);
            JOptionPane.showMessageDialog(null, "Game has been saved to " + file.getAbsolutePath());
        } catch (NullPointerException ignored) {
        }
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
