package com.services.moves;

import java.util.ArrayList;
import java.util.List;

public class AdvanceProcessors {
    private final List<AdvanceProcessor> advanceProcessors;

    public AdvanceProcessors() {
        this(new ArrayList<>());
    }

    public AdvanceProcessors(List<AdvanceProcessor> advanceProcessors) {
        this.advanceProcessors = advanceProcessors;
    }

    public void add(AdvanceProcessor advanceProcessor) {
        advanceProcessors.add(advanceProcessor);
    }

    public List<AdvanceProcessor> getAdvanceProcessors() {
        return advanceProcessors;
    }
}
