package com.models.piecesfield.fieldinitializers;

import com.globals.Defaults;
import com.models.pieces.*;
import com.models.piecesfield.Field;

import java.awt.*;
import java.util.ArrayList;

public class EmptyFieldInitializer implements FieldInitializer {

    @Override
    public Field initialize(Field originalField) {
        PieceFactory factory = new PieceFactory(PlayerType.FIRST);

        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                Point coordinates = new Point(j, i);
                if (i == 3 && j == 2 || i == 2 && j == 6) {
                    originalField.getList().add(factory.getInstance(PieceType.PAWN, coordinates));
                } else {
                    originalField.getList().add(factory.getInstance(PieceType.EMPTY, coordinates));
                }
            }
        }

        return originalField;
    }
}
