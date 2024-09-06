package com.models.pieces;

import java.awt.*;

public class PieceFactory {
    private final PlayerType playerType;

    public PieceFactory(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Piece getInstance(PieceType pieceType, Point coordinates) {
        return switch (pieceType) {
            case PAWN -> new PawnModel(playerType, coordinates);
            case EMPTY -> new EmptyPiece(coordinates);
        };
    }
}
