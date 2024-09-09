package com.services.moves.pawn;

import com.models.pieces.abstractpiece.Piece;

import java.util.List;

public class SingleForwardAdvanceProcessor extends ForwardAdvanceProcessor {
    private static final int CELLS_TO_MOVE = 1;

    public SingleForwardAdvanceProcessor(Piece piece) {
        super(piece);
    }

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(() -> addForwardBy(CELLS_TO_MOVE));
        return methodsToRun;
    }
}
