package com.services.advanceprocessors.processors;

import com.globals.Defaults;
import com.helpers.ReverseIntStream;
import com.services.advanceprocessors.advances.Advance;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OrthogonalAdvanceProcessor extends ConstrainedAdvanceProcessor {
    public OrthogonalAdvanceProcessor() {
    }

    @Override
    protected List<Advance> modifyPossibleAdvances(List<Advance> originalAdvances) {
        return originalAdvances;
    }

    public OrthogonalAdvanceProcessor(int maxDiff) {
        super(maxDiff);
    }

    @Override
    protected List<Runnable> getMethodsToRun(List<Runnable> methodsToRun) {
        methodsToRun.add(this::addTopMoves);
        methodsToRun.add(this::addBottomMoves);
        methodsToRun.add(this::addLeftMoves);
        methodsToRun.add(this::addRightMoves);
        return methodsToRun;
    }

    @Override
    protected void setupOnce() {
    }

    private void addTopMoves() {
        processMove((original) -> ReverseIntStream.rangeClosed(original.y - 1, 0).limit(getStreamLimit())
                .allMatch((i) -> addMove(original.x, i)));
    }

    private void addBottomMoves() {
        processMove((original) -> IntStream.range(original.y + 1, Defaults.TILE_AMOUNT).limit(getStreamLimit())
                .allMatch((i) -> addMove(original.x, i)));
    }

    private void addLeftMoves() {
        processMove((original) -> ReverseIntStream.rangeClosed(original.x - 1, 0).limit(getStreamLimit())
                .allMatch((i) -> addMove(i, original.y)));
    }

    private void addRightMoves() {
        processMove((original) -> IntStream.range(original.x + 1, Defaults.TILE_AMOUNT).limit(getStreamLimit())
                .allMatch((i) -> addMove(i, original.y)));
    }

    private void processMove(Consumer<Point> consumer) {
        Point originalCoordinates = getPiece().getStatus().getCoordinates();
        consumer.accept(originalCoordinates);
    }
}
