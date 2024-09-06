package com.models.pieces;

import java.awt.*;
import java.util.List;

public interface Piece extends Comparable<Piece> {
    List<Point> getAvailableMoves();

    Point getCoordinates();

    PieceType getPieceType();

    PlayerType getPlayerType();

    void moveTo(Point point) throws ArrayIndexOutOfBoundsException;
}
