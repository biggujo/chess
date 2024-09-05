package com.models.pieces;

import java.awt.*;
import java.util.List;

public class EmptyPiece extends PieceImpl {
    private static final PieceType type = PieceType.EMPTY;

    public EmptyPiece(Point coordinates, List<Point> availableMoves) {
        super(coordinates, availableMoves);
    }

    @Override
    public PieceType getType() {
        return type;
    }
}
