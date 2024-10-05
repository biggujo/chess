package com.services.advanceprocessors.processors;

abstract public class ConstrainedAdvanceProcessor extends AdvanceProcessorImpl {
    private final Integer maxDiff;

    protected ConstrainedAdvanceProcessor() {
        maxDiff = Integer.MAX_VALUE;
    }

    protected ConstrainedAdvanceProcessor(int maxDiff) {
        this.maxDiff = maxDiff;
    }

    protected long getStreamLimit() {
        return maxDiff != null ? maxDiff : Integer.MAX_VALUE;
    }

    protected Integer getMaxDiff() {
        return maxDiff;
    }
}
