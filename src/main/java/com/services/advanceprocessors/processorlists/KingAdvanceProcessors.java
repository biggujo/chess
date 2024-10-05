package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.processors.DiagonalAdvanceProcessor;
import com.services.advanceprocessors.processors.KingOrthogonalAdvanceProcessor;

public class KingAdvanceProcessors extends AdvanceProcessors {
    private static final int MAX_DIFF = 1;

    public KingAdvanceProcessors() {
        add(new KingOrthogonalAdvanceProcessor(MAX_DIFF));
        add(new DiagonalAdvanceProcessor(MAX_DIFF));
    }
}
