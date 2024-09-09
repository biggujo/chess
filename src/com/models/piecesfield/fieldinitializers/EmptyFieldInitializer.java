package com.models.piecesfield.fieldinitializers;

import com.globals.Defaults;
import com.models.pieces.*;
import com.models.piecesfield.Field;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmptyFieldInitializer implements FieldInitializer {

    @Override
    public List<Piece> initialize(List<Piece> originalField) {
        List<Piece> newFieldList = new ArrayList<>(originalField);

        PieceFactory whiteFactory = new PieceFactory(PlayerType.FIRST);
        PieceFactory blackFactory = new PieceFactory(PlayerType.SECOND);

        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                Point coordinates = new Point(j, i);
                if (i == 2 && j == 6 || i == 3 && j == 6 || i == 2 && j == 1 || i == 2 && j == 3 || i == 5 && j == 5) {
                    newFieldList.add(blackFactory.getInstance(PieceType.PAWN, coordinates));
                    continue;
                }

                if (i == 3 && j == 2 || i == 1 && j == 2 || i == 5 && j == 6) {
                    newFieldList.add(whiteFactory.getInstance(PieceType.PAWN, coordinates));
                    continue;
                }

                newFieldList.add(blackFactory.getInstance(PieceType.EMPTY, coordinates));
            }
        }

        return newFieldList;
    }
}
