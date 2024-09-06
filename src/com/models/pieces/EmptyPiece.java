package com.models.pieces;

import java.awt.*;

public class EmptyPiece extends PieceImpl {
    private static final PieceType type = PieceType.EMPTY;

    public EmptyPiece(Point coordinates) {
        super(coordinates);
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.NONE;
    }
}
