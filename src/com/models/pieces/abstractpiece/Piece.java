package com.models.pieces.abstractpiece;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.moves.Advances;

import java.awt.*;

public interface Piece extends Comparable<Piece> {
    Advances getAdvancesList();

    PieceType getPieceType();

    PlayerType getPlayerType();

    Status getStatus();

    void moveTo(Point point) throws IllegalPieceMoveException;
}
