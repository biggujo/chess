package com.services.fieldlisteners;

import com.models.pieces.abstractpiece.Piece;

import java.util.function.BiConsumer;

public interface OnMoveEndListener extends BiConsumer<Piece, Piece> {
}
