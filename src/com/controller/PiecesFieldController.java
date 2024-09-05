package com.controller;

import com.models.piecesfieldmodel.PiecesFieldModel;
import com.view.panels.PiecesPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PiecesFieldController {
    public static void handleClickAt(Point coordinates) throws IOException {
        PiecesFieldModel.registerClickAt(coordinates);

        if (!PiecesFieldModel.hasMoved()) {
            return;
        }

        updatePanel();
    }

    private static void updatePanel() {
        List<JComponent> updatedField = PiecesFieldModel.getField();

        SwingUtilities.invokeLater(() -> {
            try {
                PiecesPanel.getInstance().updateWith(updatedField);
                PiecesPanel.getInstance().revalidate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
