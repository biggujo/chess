package com.services.advanceprocessors.processors;

import com.helpers.PointTranslator;
import com.models.pieces.IllegalPieceMoveException;

import java.awt.*;
import java.util.List;

public class ForwardAdvanceProcessor extends AdvanceProcessorImpl {
    private int step;

    public ForwardAdvanceProcessor() {
        step = -1;
    }

    @Override
    protected void setupOnce() {
        if (!isFirstPlayerPiece()) {
            changeDirection();
        }
    }

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(this::addForwardMoves);
        return methodsToRun;
    }

    private void addForwardMoves() {
        boolean successfullyMovedOnePiece = addSingleForward();
        if (!successfullyMovedOnePiece) {
            return;
        }

        if (getPiece().getStatus().isMoved()) {
            return;
        }

        addDoubleForward();
    }

    private boolean addSingleForward() {
        return addForwardBy(step);
    }

    private void addDoubleForward() {
        addForwardBy(step * 2);
    }

    private boolean addForwardBy(int dy) {
        Point destCoordinates = PointTranslator.translate(getPiece().getStatus().getCoordinates(), 0, dy);
        return tryToAddForwardMoveTo(destCoordinates);
    }

    private boolean tryToAddForwardMoveTo(Point destCoordinates) throws IllegalPieceMoveException {
        try {
            if (!isBusyCellAt(destCoordinates)) {
                addAdvanceTo(destCoordinates);
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
            return false;
        }

        return false;
    }

    private void changeDirection() {
        step = -step;
    }
}
