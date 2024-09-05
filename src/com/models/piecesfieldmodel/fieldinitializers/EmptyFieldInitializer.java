package com.models.piecesfieldmodel.fieldinitializers;

import com.globals.Defaults;
import com.view.pieces.PieceType;

public class EmptyFieldInitializer implements FieldInitializer {

    @Override
    public PieceType[][] initializeField(PieceType[][] originalField) {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                originalField[i][j] = PieceType.EMPTY;
            }
        }

        return originalField;
    }
}
