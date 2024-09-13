package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.processors.LShapedAdvanceProcessor;

public class KnightAdvanceProcessors extends AdvanceProcessors {
    public KnightAdvanceProcessors() {
        add(new LShapedAdvanceProcessor());
    }
}
