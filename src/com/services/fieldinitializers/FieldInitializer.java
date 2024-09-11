package com.services.fieldinitializers;

import com.models.pieces.abstractpiece.Piece;

import java.util.List;

public interface FieldInitializer {
    List<Piece> initialize();

    List<Piece> initialize(List<Piece> predefinedField);
}
