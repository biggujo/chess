package com.models.piecesfieldmodel.fieldinitializers;

import com.view.pieces.PieceType;

public interface FieldInitializer {
    PieceType[][] initializeField(PieceType[][] originalField);
}
