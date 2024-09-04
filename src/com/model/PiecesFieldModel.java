package com.model;

import com.globals.Defaults;
import com.view.pieces.PieceFactory;
import com.view.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PiecesFieldModel {
    private final PieceType[][] fieldPieces = {
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.PAWN, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.PAWN, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
    };
    private static PiecesFieldModel instance;
    private final List<JComponent> fieldComponents = new ArrayList<>();

    public static PiecesFieldModel getInstance() throws IOException {
        if (PiecesFieldModel.instance == null) {
            PiecesFieldModel.instance = new PiecesFieldModel();
        }

        return PiecesFieldModel.instance;
    }

    private PiecesFieldModel() {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                PieceType currentType = fieldPieces[i][j];

                fieldComponents.add(PieceFactory.getInstance(currentType));
            }
        }
    }

    public PieceType getPieceTypeAt(Point coordinates) {
        return fieldPieces[coordinates.y][coordinates.x];
    }

    public void movePiece(Point initial, Point target) {
        if (initial.equals(target)) {
            return;
        }

        int initialIndex = Defaults.TILE_AMOUNT * initial.y + initial.x;
        int targetIndex = Defaults.TILE_AMOUNT * target.y + target.x;

        PieceType currentPiece = fieldPieces[initial.y][initial.x];

        fieldPieces[initial.y][initial.x] = PieceType.EMPTY;
        fieldPieces[target.y][target.x] = currentPiece;

        JComponent piece = getFieldComponents().get(initialIndex);
        fieldComponents.set(initialIndex, PieceFactory.getInstance(PieceType.EMPTY));
        fieldComponents.set(targetIndex, piece);
    }

    public List<JComponent> getFieldComponents() {
        return fieldComponents;
    }
}
