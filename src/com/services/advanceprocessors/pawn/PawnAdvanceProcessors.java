package com.services.advanceprocessors.pawn;

import com.services.advanceprocessors.AdvanceProcessors;

public class PawnAdvanceProcessors extends AdvanceProcessors {
    public PawnAdvanceProcessors() {
        add(new ForwardAdvanceProcessor());
        add(new DiagonalForwardAdvanceProcessor());
    }
}
