package com.services.advanceprocessors.pawn;

import com.models.pieces.abstractpiece.Piece;

import java.util.List;

public class DoubleForwardAdvanceProcessor extends ForwardAdvanceProcessor {
    private static final int CELLS_TO_MOVE = 2;

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(() -> addForwardBy(CELLS_TO_MOVE));
        return methodsToRun;
    }
}
