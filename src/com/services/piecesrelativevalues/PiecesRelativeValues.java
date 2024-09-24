package com.services.piecesrelativevalues;

import com.models.pieces.PieceType;

import java.util.HashMap;
import java.util.Map;

public class PiecesRelativeValues {
    private static final Map<PieceType, Integer> points;

    static {
        points = new HashMap<>();
        points.put(PieceType.PAWN, 1);
        points.put(PieceType.KNIGHT, 3);
        points.put(PieceType.BISHOP, 3);
        points.put(PieceType.ROOK, 5);
        points.put(PieceType.QUEEN, 9);
    }

    public int get(PieceType type) {
        return points.get(type);
    }
}
