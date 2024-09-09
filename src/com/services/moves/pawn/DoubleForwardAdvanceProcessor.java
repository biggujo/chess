package com.services.moves.pawn;

import com.models.pieces.abstractpiece.Piece;

public class DoubleForwardAdvanceProcessor extends ForwardAdvanceProcessor {
    public DoubleForwardAdvanceProcessor(Piece piece) {
        super(piece);
    }

    @Override
    public void obtainPossibleAdvances() {
        addForwardBy(2);
    }
}
