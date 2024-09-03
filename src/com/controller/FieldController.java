package com.controller;

import com.cell.CellType;
import com.globals.Defaults;
import com.model.FieldModel;
import com.model.PiecesFieldModel;
import com.pieces.Piece;
import com.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FieldController {
    private static FieldController instance;
    private final PiecesFieldModel piecesFieldModel = new PiecesFieldModel(FieldModel.FIELD);
    private int prevActivePieceIndex = -1;

    public static FieldController getInstance() {
        if (FieldController.instance == null) {
            FieldController.instance = new FieldController();
        }

        return instance;
    }

    private FieldController() {
    }

    public void toggleActivePiece(Point coordinates) {
        int activePieceIndex = Defaults.TILE_AMOUNT * coordinates.y + coordinates.x;

        Piece piece = (Piece) getField().get(activePieceIndex);

        if (FieldModel.FIELD[coordinates.y][coordinates.x] == PieceType.EMPTY) {
            return;
        }

        if (activePieceIndex == prevActivePieceIndex) {
            piece.setInactive();
            prevActivePieceIndex = -1;
            return;
        } else {
            piece.setActive();
        }

        if (prevActivePieceIndex != -1) {
            piece = (Piece) getField().get(prevActivePieceIndex);
            piece.setInactive();
        }

        prevActivePieceIndex = activePieceIndex;
    }

    public void movePiece(Point initial, Point target) {
        List<JComponent> fields = getField();

        int initialIndex = initial.x * initial.y;
        int targetIndex = target.x * target.y;

        JComponent piece = fields.get(initialIndex);
        fields.set(initialIndex, piece);

        fields.set(targetIndex, piece);
    }

    public List<JComponent> getField() {
        return piecesFieldModel.getField();
    }
}
