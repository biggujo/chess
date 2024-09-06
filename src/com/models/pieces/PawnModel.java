package com.models.pieces;

import com.validators.MoveValidator;

import java.awt.*;

public class PawnModel extends NonEmptyPiece {
    private static final PieceType type = PieceType.PAWN;

    private boolean hasMovedAtLeastOnce = false;

    public PawnModel(PlayerType playerType, Point coordinates) {
        super(playerType, coordinates);
    }

    void addAvailableMoves() {
        if (hasReachedTheTop()) {
            return;
        }

        addSingleForward();
        addDoubleForward();
    }

    private boolean hasReachedTheTop() {
        return getCoordinates().y == 0;
    }

    private void addDoubleForward() {
        if (hasMovedAtLeastOnce) {
            return;
        }

        addMoveToTopBy(2);
    }

    private void addSingleForward() {
        addMoveToTopBy(1);
    }

    private void addMoveToTopBy(int y) {
        Point doubleForwardDestination = getCoordinates().getLocation();
        doubleForwardDestination.translate(0, -y);

        tryToAddAvailableMove(doubleForwardDestination);
    }

    private void tryToAddAvailableMove(Point coordinates) {
        try {
            addAvailableMove(coordinates);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void addAvailableMove(Point coordinates) throws ArrayIndexOutOfBoundsException {
        if (MoveValidator.validate(coordinates)) {
            getAvailableMoves().add(coordinates);
        }
    }

    @Override
    public void setCoordinates(Point coordinates) throws ArrayIndexOutOfBoundsException {
        super.setCoordinates(coordinates);
        hasMovedAtLeastOnce = true;
    }

    @Override
    public PieceType getPieceType() {
        return PawnModel.type;
    }
}
