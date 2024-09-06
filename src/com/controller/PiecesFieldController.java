package com.controller;

import com.models.piecesfield.PiecesFieldModel;
import com.view.panels.PanelManager;

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

        updatePiecesPanel();
    }

    private static void updatePiecesPanel() {
        List<JComponent> updatedField = PiecesFieldModel.getComponents();

        SwingUtilities.invokeLater(() -> {
            try {
                PanelManager.getInstance().getPiecesPanel().updateWith(updatedField);
                PanelManager.getInstance().getPiecesPanel().revalidate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
