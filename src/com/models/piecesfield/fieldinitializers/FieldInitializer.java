package com.models.piecesfield.fieldinitializers;

import com.view.pieces.PieceType;

public interface FieldInitializer {
    PieceType[][] initializeField(PieceType[][] originalField);
}
