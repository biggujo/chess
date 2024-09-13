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

    public static AdvanceProcessors forBishop() {
        return new BishopAdvanceProcessors();
    }


    public static AdvanceProcessors forKnight() {
        return new KnightAdvanceProcessors();
    }

    public static AdvanceProcessors forKing() {
        return new KingAdvanceProcessors();
    }

    public static AdvanceProcessors forQueen() {
        return new QueenAdvanceProcessors();
    }
}
