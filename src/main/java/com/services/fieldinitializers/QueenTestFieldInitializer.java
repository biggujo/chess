package com.services.fieldinitializers;

import com.globals.Defaults;
import com.models.pieces.PieceFactory;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QueenTestFieldInitializer implements FieldInitializer {
    @Override
    public List<Piece> initialize() {
        return initialize(null);
    }

    @Override
    public List<Piece> initialize(List<Piece> predefinedField) {
        List<Piece> newFieldList = new ArrayList<>();

        if (predefinedField != null) {
            newFieldList.addAll(predefinedField);
        }

        PieceFactory whiteFactory = new PieceFactory(PlayerType.FIRST);
        PieceFactory blackFactory = new PieceFactory(PlayerType.SECOND);

        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                Point coordinates = new Point(j, i);

                if (i == 1 && j == 1 || i == 6 && j == 6 || i == 1 && j == 6 || i == 6 && j == 1) {
                    newFieldList.add(blackFactory.getInstance(PieceType.PAWN, coordinates));
                    continue;
                }

                if (i == 3 && j == 3 || i == 4 && j == 3) {
                    newFieldList.add(whiteFactory.getInstance(PieceType.QUEEN, coordinates));
                    continue;
                }

                newFieldList.add(blackFactory.getInstance(PieceType.EMPTY, coordinates));
            }
        }

        return newFieldList;
    }
}
