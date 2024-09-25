package com.services.guilisteners;

import com.controller.GameManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

public class ResetGameMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            int dialogOption = JOptionPane.showConfirmDialog(null, "Start new game?", "New Game", JOptionPane.YES_NO_OPTION);
            if (dialogOption == JOptionPane.NO_OPTION) {
                return;
            }

            GameManager.resetGame();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
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
