package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.processors.DiagonalForwardAdvanceProcessor;
import com.services.advanceprocessors.processors.ForwardAdvanceProcessor;

public class PawnAdvanceProcessors extends AdvanceProcessors {
    public PawnAdvanceProcessors() {
        add(new ForwardAdvanceProcessor());
        add(new DiagonalForwardAdvanceProcessor());
    }
}
