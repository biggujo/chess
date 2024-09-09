package com.models.pieces;

import com.models.pieces.abstractpiece.Piece;
import com.models.pieces.concretes.EmptyPiece;
import com.models.pieces.concretes.PawnModel;
import com.services.moves.AdvanceProcessors;
import com.services.moves.pawn.PawnAdvanceProcessors;

import java.awt.*;

public class PieceFactory {
    private final PlayerType playerType;

    public PieceFactory(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Piece getInstance(PieceType pieceType, Point coordinates) {
        return switch (pieceType) {
            case PAWN -> {
                PawnModel pawnModel = new PawnModel(playerType, coordinates);
                AdvanceProcessors advanceProcessors = new PawnAdvanceProcessors(pawnModel);
                pawnModel.setAdvanceProcessors(advanceProcessors);

                yield pawnModel;
            }
            case EMPTY -> new EmptyPiece(coordinates);
        };
    }
}
