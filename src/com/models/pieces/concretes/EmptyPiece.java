package com.models.pieces.concretes;

import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.PieceImpl;
import com.services.moves.AdvanceProcessors;

import java.awt.*;

public class EmptyPiece extends PieceImpl {
    private static final PieceType type = PieceType.EMPTY;

    public EmptyPiece(Point coordinates) {
        super(PlayerType.NONE, coordinates);
    }

    @Override
    protected void addPossibleAdvances() {
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.NONE;
    }
}
