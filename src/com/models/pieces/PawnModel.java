package com.models.pieces;

import com.globals.Defaults;
import com.services.moves.Advance;
import com.services.moves.AdvanceProcessor;
import com.services.moves.pawn.DoubleForwardAdvanceProcessor;
import com.services.moves.pawn.SingleForwardAdvanceProcessor;

import java.awt.*;
import java.util.List;

public class PawnModel extends NonEmptyPiece {
    private static final PieceType type = PieceType.PAWN;

    private boolean hasMovedAtLeastOnce = false;

    public PawnModel(PlayerType playerType, Point coordinates) {
        super(playerType, coordinates);
    }

    public void addPossibleAdvances() {
        if (!hasAddedAdvanceProcessors()) {
            createAdvancesProcessors();
        }

        if (hasReachedTheTop()) {
            return;
        }

        for (AdvanceProcessor c : getAdvanceProcessors()) {
            List<Advance> newPossibleAdvances = c.getPossibleAdvances();
            getAdvancesList().getAvailableAdvances().addAll(newPossibleAdvances);
        }
    }

//    private void tryToAddDiagonalMoves() {
//        try {
//            addDiagonalMoves();
//        } catch (IllegalPieceMoveException ignored) {
//        }
//    }

//    private void tryToAddEnPassant() {
//        try {
//            addEnPassant();
//        } catch (IllegalPieceMoveException ignored) {
//        }
//    }

    private boolean hasReachedTheTop() {
        if (isFirstPlayer()) {
            return getCoordinates().y == 0;
        }

        return getCoordinates().y == Defaults.TILE_AMOUNT - 1;
    }

//    private void addDiagonalMoves() {
//        int dx = -1;
//        int dy = -1;
//
//        if (!isFirstPlayer()) {
//            dy = -dy;
//        }
//
//        try {
//            addDiagonalMoveTo(dx, dy);
//        } catch (IllegalPieceMoveException ignored) {
//        }
//
//        try {
//            addDiagonalMoveTo(-dx, dy);
//        } catch (IllegalPieceMoveException ignored) {
//        }
//    }

//    private void addDiagonalMoveTo(int dx, int dy) {
//        Point destination = PointTranslator.translate(getCoordinates(), dx, dy);
//
//        Field field = PiecesFieldModel.getField();
//        try {
//            if (field.isEmptyAt(destination)) {
//                throw new IllegalPieceMoveException();
//            }
//        } catch (IndexOutOfBoundsException ignored) {
//            return;
//        }
//
//        addMoveTo(destination);
//    }

//    private void addEnPassant() {
//        int dx = -1;
//        int dy = -1;
//
//        Point destination = PointTranslator.translate(getCoordinates(), dx, dy);
//
//        Point nearToTheLeft = PointTranslator.translate(getCoordinates(), -1, 0);
//        Point nearToTheRight = PointTranslator.translate(getCoordinates(), 1, 0);
//
//        if (!PiecesFieldModel.getField().isEmptyAt(nearToTheLeft)) {
//            addMoveTo(destination);
//            // TODO: move to a class
//            PiecesFieldModel.getField().captureAt(nearToTheLeft);
//        }
//
//        if (!PiecesFieldModel.getField().isEmptyAt(nearToTheRight)) {
//            addMoveTo(destination);
//            // TODO: move to a class
//            PiecesFieldModel.getField().captureAt(nearToTheRight);
//        }
//    }

//    private void addMoveTo(Point destination) {
//        try {
//            if (MoveOutOfBoundsValidator.validateDestination(destination)) {
//                getAvailableMoves().add(destination);
//            }
//        } catch (IllegalPieceMoveException ignored) {
//        }
//    }

    private boolean isFirstPlayer() {
        return getPlayerType() == PlayerType.FIRST;
    }

    private void createAdvancesProcessors() {
        getAdvanceProcessors().add(new SingleForwardAdvanceProcessor(this));
        getAdvanceProcessors().add(new DoubleForwardAdvanceProcessor(this));
    }

    @Override
    public PieceType getPieceType() {
        return PawnModel.type;
    }
}
