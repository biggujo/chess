package com.services.advanceprocessors.processorlists;

import com.services.advanceprocessors.processors.OrthogonalAdvanceProcessor;

public class RookAdvanceProcessors extends AdvanceProcessors {
    public RookAdvanceProcessors() {
        add(new OrthogonalAdvanceProcessor());
    }
}
