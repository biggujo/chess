package com.models.piecesfield.fieldinitializers;

import com.globals.Defaults;
import com.models.piecesfield.Field;

public class EmptyFieldInitializer implements FieldInitializer {

    @Override
    public Field initialize(Field originalField) {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                // TODO: add piece fill
//                originalField.getField().get(i).set(j, )[i][j] = PieceType.EMPTY;
            }
        }

        return originalField;
    }
}
