package com.models.pieces;

import com.models.pieces.abstractpiece.Piece;
import com.models.pieces.concretes.EmptyPiece;
import com.models.pieces.concretes.PawnModel;
import com.models.pieces.concretes.RookModel;

import java.awt.*;

public class PieceFactory {
    private final PlayerType playerType;

    public PieceFactory(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Piece getInstance(PieceType pieceType, Point coordinates) {
        return switch (pieceType) {
            case PAWN -> new PawnModel(playerType, coordinates);
            case ROOK -> new RookModel(playerType, coordinates);
            case EMPTY -> new EmptyPiece(coordinates);
        };
    }
}
