package com.services.advanceprocessors.pawn;

import java.util.List;

public class SingleForwardAdvanceProcessor extends ForwardAdvanceProcessor {
    private static final int CELLS_TO_MOVE = 1;

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(() -> addForwardBy(CELLS_TO_MOVE));
        return methodsToRun;
    }
}
