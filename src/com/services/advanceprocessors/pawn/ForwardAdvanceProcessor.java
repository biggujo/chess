package com.services.advanceprocessors.pawn;

import com.helpers.PointTranslator;
import com.models.pieces.IllegalPieceMoveException;
import com.models.piecesfield.PiecesFieldModel;
import com.services.advanceprocessors.Advance;
import com.services.advanceprocessors.AdvanceProcessorImpl;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

class ForwardAdvanceProcessor extends AdvanceProcessorImpl {
    private int step;

    public ForwardAdvanceProcessor() {
        step = -1;
    }

    @Override
    protected void setup() {
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
                Advance advance = new Advance(destCoordinates, null);
                add(advance);
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
            return false;
        }

        return false;
    }

    private boolean isBusyCellAt(Point coordinates) throws IndexOutOfBoundsException {
        return !PiecesFieldModel.getInstance().getField().isEmptyAt(coordinates);
    }

    private void changeDirection() {
        step = -step;
    }
}
