package com.services.fieldlisteners;

import com.models.pieces.abstractpiece.Piece;

public interface FieldListener {
    void onMoveEnds(Piece originalPiece, Piece capturedPiece);
}
