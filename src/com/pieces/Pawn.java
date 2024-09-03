package com.pieces;

import java.awt.*;

class Pawn extends Piece {
    private static final String ICON_NAME = "pawn.png";

    public Pawn(Dimension dimension) {
        super(dimension, ICON_NAME);
    }
}
