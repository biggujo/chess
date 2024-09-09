package com.services.moves.pawn;

import com.helpers.PointTranslator;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.services.moves.Advance;
import com.services.moves.AdvanceProcessorImpl;

import java.awt.*;
import java.util.List;


public class DiagonalForwardAdvanceProcessor extends AdvanceProcessorImpl {
    private final int dx;
    private int dy;

    public DiagonalForwardAdvanceProcessor(Piece piece) {
        super(piece);

        dx = -1;
        dy = -1;

        if (!isFirstPlayerPiece()) {
            dy = -dy;
        }
    }

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(this::addDiagonalMoveToTheLeft);
        methodsToRun.add(this::addDiagonalMoveToTheRight);
        methodsToRun.add(this::addEnPassantToTheLeft);
        methodsToRun.add(this::addEnPassantToTheRight);

        return methodsToRun;
    }

    private void addDiagonalMoveToTheLeft() {
        addDiagonalMoveTo(-dx, dy);
    }

    private void addDiagonalMoveToTheRight() {
        addDiagonalMoveTo(dx, dy);
    }

    private void addEnPassantToTheLeft() {
        addDiagonalMoveTo(dx, dy, -dx, 0);
    }

    private void addEnPassantToTheRight() {
        addDiagonalMoveTo(dx, dy, dx, 0);
    }

    private void addDiagonalMoveTo(int dx, int dy) {
        try {
            addDiagonalMoveTo(dx, dy, dx, dy);
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private void addDiagonalMoveTo(int dx, int dy, int captureDx, int captureDy) {
        Point currentCoordinates = getPiece().getStatus().getCoordinates();

        Point destination = PointTranslator.translate(currentCoordinates, dx, dy);
        Point captureCoordinates = PointTranslator.translate(currentCoordinates, captureDx, captureDy);

        Field field = PiecesFieldModel.getField();
        try {
            if (field.isEmptyAt(captureCoordinates)) {
                throw new IllegalPieceMoveException();
            }
        } catch (IndexOutOfBoundsException ignored) {
            return;
        }

        add(new Advance(destination, captureCoordinates));
    }
}
