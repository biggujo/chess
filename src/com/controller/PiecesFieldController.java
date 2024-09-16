package com.controller;

import com.models.piecesfield.PiecesFieldModel;
import com.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PiecesFieldController {
    public static void handleClickAt(Point coordinates) throws IOException {
        PiecesFieldModel.getInstance().registerClickAt(coordinates);

        if (!PiecesFieldModel.getInstance().isTheLastMoveSuccessful()) {
            return;
        }

        updatePiecesPanel();
    }

    public static void updatePiecesPanel() {
        PiecesFieldModel.getInstance().getFieldManager().initializeComponents();
        List<JComponent> updatedField = PiecesFieldModel.getInstance().getComponents();

        SwingUtilities.invokeLater(() -> {
            ViewManager.getInstance().getPiecesPanel().updateWith(updatedField);
            ViewManager.getInstance().getPiecesPanel().revalidate();
        });
    }
}
