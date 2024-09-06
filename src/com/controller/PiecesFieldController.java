package com.controller;

import com.models.pieces.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.view.panels.AvailableMovesPanel;
import com.view.panels.PiecesPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PiecesFieldController {
    public static void handleClickAt(Point coordinates) throws IOException {
        PiecesFieldModel.registerClickAt(coordinates);

        if (!PiecesFieldModel.hasMoved()) {
            updateAvailableMovesPanel(coordinates);
            return;
        }

        clearAvailableMovesPanel();
        updatePiecesPanel();
    }

    private static void updatePiecesPanel() {
        List<JComponent> updatedField = PiecesFieldModel.getComponents();

        SwingUtilities.invokeLater(() -> {
            try {
                PiecesPanel.getInstance().updateWith(updatedField);
                PiecesPanel.getInstance().revalidate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void updateAvailableMovesPanel(Point coordinates) {
        Piece piece = PiecesFieldModel.getField().get(coordinates);

        List<Point> availableMoves = piece.getAvailableMoves();

        AvailableMovesPanel.getInstance(null).setAvailableMoves(availableMoves);
    }

    private static void clearAvailableMovesPanel() {
        AvailableMovesPanel.getInstance(null).setAvailableMoves(new ArrayList<>());
    }
}
