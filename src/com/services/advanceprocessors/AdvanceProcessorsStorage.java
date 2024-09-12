package com.services.advanceprocessors;

import com.services.advanceprocessors.empty.EmptyAdvanceProcessors;
import com.services.advanceprocessors.pawn.PawnAdvanceProcessors;

public class AdvanceProcessorsStorage {
    public static AdvanceProcessors forPawn() {
        return new PawnAdvanceProcessors();
    }

    public static AdvanceProcessors forEmpty() {
        return new EmptyAdvanceProcessors();
    }
}
