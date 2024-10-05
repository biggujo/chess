package com.services.advanceprocessors.processors;

import com.helpers.PointTranslator;
import com.models.pieces.IllegalPieceMoveException;
import com.services.advanceprocessors.advances.Advance;

import java.awt.*;
import java.util.List;


public class DiagonalForwardAdvanceProcessor extends AdvanceProcessorImpl {
    private static final int X_DIFF = 1;
    private int dy;

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(this::addDiagonalMoveToTheLeft);
        methodsToRun.add(this::addDiagonalMoveToTheRight);
        methodsToRun.add(this::addEnPassantToTheLeft);
        methodsToRun.add(this::addEnPassantToTheRight);

        return methodsToRun;
    }

    @Override
    protected void setupOnce() {
        dy = -1;

        if (!isFirstPlayerPiece()) {
            dy = -dy;
        }
    }

    private void addDiagonalMoveToTheLeft() {
        addDiagonalMoveTo(-X_DIFF, dy);
    }

    private void addDiagonalMoveToTheRight() {
        addDiagonalMoveTo(X_DIFF, dy);
    }

    private void addEnPassantToTheLeft() {
        addDiagonalMoveTo(-X_DIFF, dy, -X_DIFF, 0);
    }

    private void addEnPassantToTheRight() {
        addDiagonalMoveTo(X_DIFF, dy, X_DIFF, 0);
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

        try {
            if (getField().isEmptyAt(captureCoordinates)) {
                throw new IllegalPieceMoveException();
            }
        } catch (IndexOutOfBoundsException ignored) {
            return;
        }

        addAdvanceToWithCapture(destination, captureCoordinates);
    }

    @Override
    protected List<Advance> modifyPossibleAdvances(List<Advance> originalAdvances) {
        return originalAdvances;
    }
}
