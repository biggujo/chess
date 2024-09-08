package com.models.pieces;

import com.globals.Defaults;
import com.models.piecesfield.PiecesFieldModel;
import com.validators.MoveOutOfBoundsValidator;

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

        tryToAddMoves();
    }

    private void tryToAddMoves() {
        try {
            addSingleForward();
            addDoubleForward();
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private boolean hasReachedTheTop() {
        if (isFirstPlayer()) {
            return getCoordinates().y == 0;
        }

        return getCoordinates().y == Defaults.TILE_AMOUNT - 1;
    }

    private void addDoubleForward() {
        if (hasMovedAtLeastOnce) {
            return;
        }

        int dy = -2;

        if (isFirstPlayer()) {
            dy = -dy;
        }

        addMoveToTopBy(dy);
    }

    private void addSingleForward() {
        int dy = -1;

        if (isFirstPlayer()) {
            dy = -dy;
        }

        addMoveToTopBy(dy);
    }

    private void addMoveToTopBy(int y) {
        Point destination = getCoordinates().getLocation();
        destination.translate(0, -y);

        try {
            MoveOutOfBoundsValidator.validate(destination);
        } catch (IllegalPieceMoveException ignored) {
            return;
        }

        if (PiecesFieldModel.getField().isNotEmptyAt(destination)) {
            throw new IllegalPieceMoveException();
        }

        tryToAddAvailableMove(destination);
    }

    private void tryToAddAvailableMove(Point coordinates) {
        try {
            addAvailableMove(coordinates);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void addAvailableMove(Point coordinates) throws ArrayIndexOutOfBoundsException {
        if (MoveOutOfBoundsValidator.validate(coordinates)) {
            getAvailableMoves().add(coordinates);
        }
    }

    private boolean isFirstPlayer() {
        return getPlayerType() == PlayerType.FIRST;
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
