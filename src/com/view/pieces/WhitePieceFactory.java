package com.view.pieces;

import com.helpers.IconPaths;
import com.models.pieces.Piece;
import com.models.pieces.PieceType;

import javax.swing.*;
import java.awt.*;

class WhitePieceFactory {
    private final Dimension dimension;

    WhitePieceFactory(Dimension dimension) {
        this.dimension = dimension;
    }

    JComponent getInstance(Piece piece) {
        if (piece.getPieceType() == PieceType.PAWN) {
            return new Pawn(dimension, IconPaths.PAWN_WHITE);
        }

        throw new IllegalArgumentException();
    }
}
