package com.models.pieces;

import com.services.moves.Advances;

import java.awt.*;

public interface Piece extends Comparable<Piece> {
    Advances getAdvancesList();

    Point getCoordinates();

    PieceType getPieceType();

    PlayerType getPlayerType();

    void moveTo(Point point) throws ArrayIndexOutOfBoundsException;
}
