package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.empty.EmptyAdvanceProcessors;

public class AdvanceProcessorsStorage {
    public static AdvanceProcessors forPawn() {
        return new PawnAdvanceProcessors();
    }

    public static AdvanceProcessors forEmpty() {
        return new EmptyAdvanceProcessors();
    }

    public static AdvanceProcessors forRook() {
        return new RookAdvanceProcessors();
    }
}
