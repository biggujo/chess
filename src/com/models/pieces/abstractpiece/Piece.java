package com.models.pieces.abstractpiece;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.advanceprocessors.advances.Advances;

import java.awt.*;

public interface Piece extends Comparable<Piece> {
    Advances resolveAdvancesList();

    PieceType getPieceType();

    PlayerType getPlayerType();

    Status getStatus();

    boolean hasReachedTheTop();

    int getValue();

    void moveTo(Point point) throws IllegalPieceMoveException;
}
