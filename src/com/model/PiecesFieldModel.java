package com.model;

import com.pieces.PieceFactory;
import com.pieces.PieceType;
import com.globals.Defaults;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PiecesFieldModel {
    private final List<JComponent> field = new ArrayList<>();

    public PiecesFieldModel(PieceType[][] piecesLocation) {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                PieceType currentType = piecesLocation[i][j];

                field.add(PieceFactory.getInstance(currentType));
            }
        }
    }

    public List<JComponent> getField() {
        return field;
    }
}
