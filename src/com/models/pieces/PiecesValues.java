package com.models.pieces;

import java.util.HashMap;

public class PiecesValues {
    private static final HashMap<PieceType, Integer> PIECE_TYPE_INTEGER_MAP;

    static {
        PIECE_TYPE_INTEGER_MAP = new HashMap<>();
        PIECE_TYPE_INTEGER_MAP.put(PieceType.EMPTY, Integer.valueOf(0));
        PIECE_TYPE_INTEGER_MAP.put(PieceType.KING, Integer.valueOf(0));
        PIECE_TYPE_INTEGER_MAP.put(PieceType.PAWN, Integer.valueOf(1));
        PIECE_TYPE_INTEGER_MAP.put(PieceType.BISHOP, Integer.valueOf(3));
        PIECE_TYPE_INTEGER_MAP.put(PieceType.KNIGHT, Integer.valueOf(3));
        PIECE_TYPE_INTEGER_MAP.put(PieceType.ROOK, Integer.valueOf(5));
        PIECE_TYPE_INTEGER_MAP.put(PieceType.QUEEN, Integer.valueOf(9));
    }

    public static int get(PieceType pieceType) {
        return PIECE_TYPE_INTEGER_MAP.get(pieceType);
    }
}
