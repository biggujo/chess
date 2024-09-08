package com.models.pieces;

import com.globals.Defaults;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.validators.MoveOutOfBoundsValidator;

import java.awt.*;
import java.lang.reflect.Array;

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

        tryToAddForwardMoves();
        tryToAddDiagonalMoves();
    }

    private void tryToAddDiagonalMoves() {
        try {
            addDiagonalMoves();
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private void tryToAddForwardMoves() {
        try {
            addSingleForward();
            addDoubleForward();
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private void addDiagonalMoves() {
        int dx = -1;
        int dy = -1;

        if (!isFirstPlayer()) {
            dy = -dy;
        }

        try {
            addDiagonalMoveTo(dx, dy);
        } catch (IllegalPieceMoveException ignored) {
        }

        try {
            addDiagonalMoveTo(-dx, dy);
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private void addDiagonalMoveTo(int dx, int dy) {
        Point destination = getCoordinates().getLocation();
        destination.translate(dx, dy);

        Field field = PiecesFieldModel.getField();
        try {
            if (field.isEmptyAt(destination)) {
                throw new IllegalPieceMoveException();
            }
        } catch (IndexOutOfBoundsException ignored) {
            return;
        }

        addMoveTo(destination);
    }

    private boolean hasReachedTheTop() {
        if (isFirstPlayer()) {
            return getCoordinates().y == 0;
        }

        return getCoordinates().y == Defaults.TILE_AMOUNT - 1;
    }

    private void addSingleForward() {
        int dy = 1;

        if (isFirstPlayer()) {
            dy = -dy;
        }

        addForwardBy(dy);
    }

    private void addDoubleForward() {
        if (hasMovedAtLeastOnce) {
            return;
        }

        int dy = 2;

        if (isFirstPlayer()) {
            dy = -dy;
        }

        addForwardBy(dy);
    }

    private void addForwardBy(int dy) throws IllegalPieceMoveException {
        Point destination = getCoordinates().getLocation();
        destination.translate(0, dy);

        try {
            if (!PiecesFieldModel.getField().isEmptyAt(destination)) {
                throw new IllegalPieceMoveException();
            }
        } catch (IndexOutOfBoundsException ignored) {
            return;
        }

        addMoveTo(destination);
    }

    private void addMoveTo(Point destination) {
        try {
            addAvailableMoveTo(destination);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void addAvailableMoveTo(Point coordinates) throws ArrayIndexOutOfBoundsException {
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
