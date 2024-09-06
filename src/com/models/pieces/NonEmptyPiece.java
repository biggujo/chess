package com.models.pieces;

import com.validators.MoveValidator;

import java.awt.*;
import java.util.List;

public abstract class NonEmptyPiece extends PieceImpl {
    public NonEmptyPiece(Point coordinates, List<Point> availableMoves) {
        super(coordinates, availableMoves);

        addAvailableMoves();
    }

    @Override
    public void moveTo(Point givenCoordinates) throws IllegalPieceMoveException {
        super.moveTo(givenCoordinates);

        if (!isMoveLegalTo(givenCoordinates)) {
            throw new IllegalPieceMoveException();
        }

        recalculateAvailableMoves();
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
