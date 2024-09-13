package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.processors.DiagonalAdvanceProcessor;
import com.services.advanceprocessors.processors.OrthogonalAdvanceProcessor;

public class QueenAdvanceProcessors extends AdvanceProcessors {
    public QueenAdvanceProcessors() {
        add(new OrthogonalAdvanceProcessor());
        add(new DiagonalAdvanceProcessor());
    }
}
