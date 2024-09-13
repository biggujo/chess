package com.services.advanceprocessors.processors;

import com.helpers.PointTranslator;
import com.validators.MoveOutOfBoundsValidator;

import java.awt.*;
import java.util.List;

public class DiagonalAdvanceProcessor extends ConstrainedAdvanceProcessor {
    public DiagonalAdvanceProcessor() {
    }

    public DiagonalAdvanceProcessor(int maxDiff) {
        super(maxDiff);
    }

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(this::addLeftTopDiagonalMoves);
        methodsToRun.add(this::addRightTopDiagonalMoves);
        methodsToRun.add(this::addRightBottomDiagonalMoves);
        methodsToRun.add(this::addLeftBottomDiagonalMoves);
        return methodsToRun;
    }

    @Override
    protected void setupOnce() {
    }

    private void addLeftTopDiagonalMoves() {
        addDiagonalMoves(-1, -1);
    }

    private void addRightTopDiagonalMoves() {
        addDiagonalMoves(1, -1);
    }

    private void addRightBottomDiagonalMoves() {
        addDiagonalMoves(1, 1);
    }

    private void addLeftBottomDiagonalMoves() {
        addDiagonalMoves(-1, 1);
    }

    private void addDiagonalMoves(int dx, int dy) {
        Point original = getPiece().getStatus().getCoordinates();

        Point newPoint = original.getLocation();
        newPoint.translate(dx, dy);

        boolean isPointValid = MoveOutOfBoundsValidator.validateDestination(newPoint);

        int iterations = 0;
        while (isPointValid) {
            boolean anotherMovesAllowed = addMove(newPoint.x, newPoint.y);

            if (!anotherMovesAllowed) {
                break;
            }

            newPoint = PointTranslator.translate(newPoint, dx, dy);
            isPointValid = MoveOutOfBoundsValidator.validateDestination(newPoint);
            iterations += 1;

            if (hasReachedTheLimit(iterations)) {
                break;
            }
        }
    }

    private boolean hasReachedTheLimit(int currentValue) {
        return currentValue >= getMaxDiff();
    }
}
