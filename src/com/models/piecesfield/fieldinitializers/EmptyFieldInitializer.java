package com.models.piecesfield.fieldinitializers;

import com.globals.Defaults;
import com.models.pieces.EmptyPiece;
import com.models.pieces.PawnModel;
import com.models.piecesfield.Field;

import java.awt.*;
import java.util.ArrayList;

public class EmptyFieldInitializer implements FieldInitializer {

    @Override
    public Field initialize(Field originalField) {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                Point coordinates = new Point(i, j);
                if (i == 2 && j == 3) {
                    originalField.getList().add(new PawnModel(coordinates, new ArrayList<>()));
                } else {
                    originalField.getList().add(new EmptyPiece(coordinates, new ArrayList<>()));
                }
            }
        }

        return originalField;
    }
}
