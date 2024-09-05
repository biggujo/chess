package com.models.piecesfield;

import com.models.piecesfield.fieldinitializers.EmptyFieldInitializer;
import com.models.piecesfield.fieldinitializers.FieldInitializer;
import com.view.pieces.PieceType;

import java.awt.*;

class FieldTypesManager {
    private final FieldTypesEntity fieldTypes;

    public FieldTypesManager() {
        FieldInitializer initializer = new EmptyFieldInitializer();
        fieldTypes = new FieldTypesEntity(initializer);
    }

    public PieceType getPieceTypeAt(Point coordinates) {
        return fieldTypes.getField()[coordinates.y][coordinates.x];
    }

    public void swap(Point src, Point dest) {
        PieceType currentPiece = fieldTypes.getField()[src.y][src.x];

        fieldTypes.getField()[src.y][src.x] = PieceType.EMPTY;
        fieldTypes.getField()[dest.y][dest.x] = currentPiece;
    }

    public PieceType[][] getFieldTypes() {
        return fieldTypes.getField();
    }

}
