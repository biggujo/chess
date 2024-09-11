package com.services.advanceprocessors;

import com.services.advanceprocessors.empty.EmptyAdvanceProcessors;
import com.services.advanceprocessors.pawn.PawnAdvanceProcessors;

public enum AdvanceProcessorsStorage {
    PAWN(new PawnAdvanceProcessors()),
    EMPTY(new EmptyAdvanceProcessors());

    private final AdvanceProcessors advanceProcessors;

    AdvanceProcessorsStorage(AdvanceProcessors advanceProcessors) {
        this.advanceProcessors = advanceProcessors;
    }

    public AdvanceProcessors getAdvanceProcessors() {
        return advanceProcessors;
    }
}
