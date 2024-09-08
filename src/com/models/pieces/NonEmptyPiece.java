package com.models.pieces;

import com.validators.MoveOutOfBoundsValidator;

import java.awt.*;

public abstract class NonEmptyPiece extends PieceImpl {
    private final PlayerType playerType;

    public NonEmptyPiece(PlayerType playerType, Point coordinates) {
        super(coordinates);
        this.playerType = playerType;
    }

    @Override
    public void moveTo(Point givenCoordinates) throws IllegalPieceMoveException {
        super.moveTo(givenCoordinates);
        calculateAvailableMoves();
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setCoordinates(Point coordinates) throws IllegalPieceMoveException {
        if (MoveOutOfBoundsValidator.validateDestination(coordinates)) {
            super.setCoordinates(coordinates);
        }
    }

    public void calculateAvailableMoves() {
        clearAvailableMoves();
        addPossibleAdvances();
    }
}
