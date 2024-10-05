package com.services.advanceprocessors.processors;

import com.services.advanceprocessors.advances.Advance;

import java.awt.*;
import java.util.List;

public class LShapedAdvanceProcessor extends AdvanceProcessorImpl {
    private static final Point[] POSSIBLE_DIFF_ADVANCES = {
            new Point(-1, -2),
            new Point(1, -2),
            new Point(-1, 2),
            new Point(1, 2),
            new Point(-2, -1),
            new Point(-2, 1),
            new Point(2, -1),
            new Point(2, 1),
    };

    @Override
    protected List<Advance> modifyPossibleAdvances(List<Advance> originalAdvances) {
        return originalAdvances;
    }

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(this::addMoves);
        return methodsToRun;
    }

    @Override
    protected void setupOnce() {
    }

    private void addMoves() {
        for (Point point : LShapedAdvanceProcessor.POSSIBLE_DIFF_ADVANCES) {
            Point newCoordinates = getPiece().getStatus().getCoordinates().getLocation();
            newCoordinates.translate(point.x, point.y);

            try {
                addMove(newCoordinates);
            } catch (RuntimeException ignored) {
            }
        }
    }
}
