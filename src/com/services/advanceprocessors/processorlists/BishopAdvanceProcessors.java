package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.processors.DiagonalAdvanceProcessor;

public class BishopAdvanceProcessors extends AdvanceProcessors {
    public BishopAdvanceProcessors() {
        add(new DiagonalAdvanceProcessor());
    }
}
