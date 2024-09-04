package com.model.piecesfieldmodel;

import com.view.pieces.PieceType;

import java.awt.*;

class FieldTypes {
    private final PieceType[][] fieldTypes = {
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.PAWN, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.PAWN, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
            {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
    };

    public PieceType getPieceTypeAt(Point coordinates) {
        return fieldTypes[coordinates.y][coordinates.x];
    }

    public void swap(Point src, Point dest) {
        PieceType currentPiece = fieldTypes[src.y][src.x];

        fieldTypes[src.y][src.x] = PieceType.EMPTY;
        fieldTypes[dest.y][dest.x] = currentPiece;
    }

    public PieceType[][] getFieldTypes() {
        return fieldTypes;
    }
}
