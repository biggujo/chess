package com.models.pieces;

import java.util.HashMap;

public class PiecesValues {
    private static final HashMap<PieceType, Integer> PIECE_TYPE_INTEGER_MAP;

    static {
        PIECE_TYPE_INTEGER_MAP = new HashMap<>();
        PIECE_TYPE_INTEGER_MAP.put(PieceType.EMPTY, 0);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.KING, 0);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.PAWN, 1);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.BISHOP, 3);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.KNIGHT, 3);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.ROOK, 5);
        PIECE_TYPE_INTEGER_MAP.put(PieceType.QUEEN, 9);
    }

    public static int get(PieceType pieceType) {
        return PIECE_TYPE_INTEGER_MAP.get(pieceType);
    }
}
