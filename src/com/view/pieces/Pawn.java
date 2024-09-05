package com.view.pieces;

import com.helpers.PiecesIconLoader;
import com.helpers.PiecesIconPaths;

import java.awt.*;

final class Pawn extends PieceComponent {
    public Pawn(Dimension dimension) {
        super(dimension, PiecesIconLoader.fetchImageFrom(PiecesIconPaths.PAWN));
    }
}
