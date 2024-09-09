package com.services.moves.pawn;

import com.models.pieces.concretes.PawnModel;
import com.services.moves.AdvanceProcessors;

public class PawnAdvanceProcessors extends AdvanceProcessors {
    public PawnAdvanceProcessors(PawnModel pawnModel) {
        add(new SingleForwardAdvanceProcessor(pawnModel));
        add(new DoubleForwardAdvanceProcessor(pawnModel));
        add(new DiagonalForwardAdvanceProcessor(pawnModel));
    }
}
