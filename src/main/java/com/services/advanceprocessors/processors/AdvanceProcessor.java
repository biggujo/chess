package com.services.advanceprocessors.processors;

import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.Field;
import com.services.advanceprocessors.advances.Advance;

import java.util.List;

public interface AdvanceProcessor {
    List<Advance> getPossibleAdvances(Field field, Piece piece);
}
