package com.services.listeners;

import com.controller.AvailableMovesController;
import com.controller.PiecesFieldController;
import com.controller.SwapPlayersController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwapPlayerColorsMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            SwapPlayersController.swapPlayers();
            AvailableMovesController.clearAvailableMovesPanel();
        } catch (IllegalStateException ignored) {
            JOptionPane.showMessageDialog(null, "You can't change colors since a one move has been made");
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
