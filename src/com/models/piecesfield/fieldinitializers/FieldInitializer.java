package com.models.piecesfield.fieldinitializers;

import com.models.pieces.Piece;

import java.util.List;

public interface FieldInitializer {
    List<Piece> initialize(List<Piece> originalField);
}
