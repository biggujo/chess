package com.services.moves.pawn;

import com.models.pieces.abstractpiece.Piece;

public class SingleForwardAdvanceProcessor extends ForwardAdvanceProcessor {
    public SingleForwardAdvanceProcessor(Piece piece) {
        super(piece);
    }

    @Override
    public void obtainPossibleAdvances() {
        addForwardBy(1);
    }
}
