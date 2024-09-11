package com.services.advanceprocessors;

import com.models.pieces.abstractpiece.Piece;

import java.util.List;

public interface AdvanceProcessor {
    List<Advance> getPossibleAdvances(Piece piece);
}
