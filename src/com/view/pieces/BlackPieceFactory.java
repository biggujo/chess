package com.view.pieces;

import com.helpers.IconPaths;
import com.models.pieces.Piece;

import javax.swing.*;
import java.awt.*;

class BlackPieceFactory {
    private final Dimension dimension;

    BlackPieceFactory(Dimension dimension) {
        this.dimension = dimension;
    }

    JComponent getInstance(Piece piece) {
        return switch (piece.getPieceType()) {
            case PAWN -> new Pawn(dimension, IconPaths.PAWN_BLACK);
            default -> throw new IllegalArgumentException();
        };
    }
}
