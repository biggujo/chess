package com.models.pieces.concretes;

import com.globals.Defaults;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.PieceImpl;

import java.awt.*;

public class PawnModel extends PieceImpl {
    private static final PieceType type = PieceType.PAWN;

    public PawnModel(PlayerType playerType, Point coordinates) {
        super(playerType, coordinates);
    }

    @Override
    public void addPossibleAdvances() {
        if (hasReachedTheTop()) {
            return;
        }

        super.addPossibleAdvances();
    }

    private boolean hasReachedTheTop() {
        if (isFirstPlayer()) {
            return getStatus().getCoordinates().y == 0;
        }

        return getStatus().getCoordinates().y == Defaults.TILE_AMOUNT - 1;
    }

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

    @Override
    public PieceType getPieceType() {
        return PawnModel.type;
    }
}
