package com.view.panels;

import com.controller.PiecesFieldController;
import com.helpers.CellIndexByPointSearch;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class PiecesMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point indexCoordinates = CellIndexByPointSearch.getIndex(e.getPoint());
        try {
            PiecesFieldController.getInstance().toggleActivePiece(indexCoordinates);
        } catch (IOException ex) {
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
