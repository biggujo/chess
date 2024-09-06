package com.models.pieces;

import com.validators.MoveValidator;

import java.awt.*;

public abstract class NonEmptyPiece extends PieceImpl {
    private final PlayerType playerType;

    public NonEmptyPiece(PlayerType playerType, Point coordinates) {
        super(coordinates);
        this.playerType = playerType;

        addAvailableMoves();
    }

    @Override
    public void moveTo(Point givenCoordinates) throws IllegalPieceMoveException {
        super.moveTo(givenCoordinates);

//        if (!isMoveLegalTo(givenCoordinates)) {
//            throw new IllegalPieceMoveException();
//        }

        recalculateAvailableMoves();
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setCoordinates(Point coordinates) throws IllegalPieceMoveException {
        if (MoveValidator.validate(coordinates)) {
            super.setCoordinates(coordinates);
        }
    }

    private void recalculateAvailableMoves() {
        clearAvailableMoves();
        addAvailableMoves();
    }

    private boolean isMoveLegalTo(Point givenCoordinates) {
        return getAvailableMoves().stream().anyMatch(p -> p.equals(givenCoordinates));
    }

    abstract void addAvailableMoves();
}
