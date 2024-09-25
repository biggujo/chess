package com.controller;

import com.models.pieces.PieceFactory;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesFieldController {
    public static void replacePieceAt(Point coordinates, PlayerType playerType, PieceType pieceType) {
        Field field = PiecesFieldModel.getInstance().getFieldManager().getField();

        PieceFactory factory = new PieceFactory(playerType);
        Piece newPiece = factory.getInstance(pieceType, coordinates);
        field.set(newPiece);

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

    public static void enableField() {
        PiecesFieldModel.getInstance().setDisabled(false);
    }

    public static void disableField() {
        PiecesFieldModel.getInstance().setDisabled(true);
    }
}
