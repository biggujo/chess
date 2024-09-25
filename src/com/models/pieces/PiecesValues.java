package com.models.pieces;

import java.util.HashMap;

public class PiecesValues {
    private static final HashMap<PieceType, Integer> PIECE_TYPE_INTEGER_MAP;

    static {
        PIECE_TYPE_INTEGER_MAP = new HashMap<>();
        PIECE_TYPE_INTEGER_MAP.put(PieceType.EMPTY, (Integer) 0);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.KING, (Integer) 0);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.PAWN, (Integer) 1);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.BISHOP, (Integer) 3);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.KNIGHT, (Integer) 3);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.ROOK, (Integer) 5);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.QUEEN, (Integer) 9);
    }

    public static int get(PieceType pieceType) {
        return PIECE_TYPE_INTEGER_MAP.get(pieceType);
    }
}
