package com.models.pieces;

import java.awt.*;
import java.util.List;

public interface PieceModel {
    List<Point> getAvailableMoves();

    void moveTo(Point point) throws ArrayIndexOutOfBoundsException;
}
