package com.models.pieces;

import com.globals.Defaults;
import com.helpers.PointTranslator;
import com.models.piecesfield.Field;
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

        tryToAddForwardMoves();
        tryToAddDiagonalMoves();
        tryToAddEnPassant();
    }

    private void tryToAddForwardMoves() {
        try {
            addSingleForward();
            addDoubleForward();
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private void tryToAddDiagonalMoves() {
        try {
            addDiagonalMoves();
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private void tryToAddEnPassant() {
        try {
            addEnPassant();
        } catch (IllegalPieceMoveException ignored) {
        }
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
        Point destination = PointTranslator.translate(getCoordinates(), 0, dy);

        try {
            if (!PiecesFieldModel.getField().isEmptyAt(destination)) {
                throw new IllegalPieceMoveException();
            }
        } catch (IndexOutOfBoundsException ignored) {
            return;
        }

        addMoveTo(destination);
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
        Point destination = PointTranslator.translate(getCoordinates(), dx, dy);

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

    private void addEnPassant() {
        int dx = -1;
        int dy = -1;

        Point destination = PointTranslator.translate(getCoordinates(), dx, dy);

        Point nearToTheLeft = PointTranslator.translate(getCoordinates(), -1, 0);
        Point nearToTheRight = PointTranslator.translate(getCoordinates(), 1, 0);

        if (!PiecesFieldModel.getField().isEmptyAt(nearToTheLeft)) {
            addMoveTo(destination);
            PiecesFieldModel.getField().captureAt(nearToTheLeft);
        }

        if (!PiecesFieldModel.getField().isEmptyAt(nearToTheRight)) {
            addMoveTo(destination);
            PiecesFieldModel.getField().captureAt(nearToTheRight);
        }
    }

    private void addMoveTo(Point destination) {
        try {
            if (MoveOutOfBoundsValidator.validate(destination)) {
                getAvailableMoves().add(destination);
            }
        } catch (IllegalPieceMoveException ignored) {
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
