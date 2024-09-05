package com.models.piecesfield;

import com.globals.Defaults;
import com.models.piecesfield.fieldinitializers.FieldInitializer;
import com.view.pieces.PieceType;

class FieldTypesEntity {
    private final PieceType[][] field;

    public FieldTypesEntity(FieldInitializer fieldInitializer) {
        PieceType[][] fieldToBeInitialized = new PieceType[Defaults.TILE_AMOUNT][Defaults.TILE_AMOUNT];
        field = fieldInitializer.initializeField(fieldToBeInitialized);
    }

    public PieceType[][] getField() {
        return field;
    }
}
