package com.services.advanceprocessors;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.validators.MoveOutOfBoundsValidator;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

abstract public class AdvanceProcessorImpl implements AdvanceProcessor, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Piece piece;
    private final List<Advance> possibleAdvances;
    private final List<Runnable> methodsToRun;
    private boolean hasSetUp;

    public AdvanceProcessorImpl() {
        this.possibleAdvances = new ArrayList<>();

        methodsToRun = getMethodsToRun(new ArrayList<>());
    }

    private void obtainPossibleAdvances() {
        methodsToRun.forEach((m) -> {
            try {
                m.run();
            } catch (IllegalPieceMoveException ignored) {
            }
        });
    }

    protected abstract List<Runnable> getMethodsToRun(List<Runnable> methodsToRun);

    protected void add(Advance advance) {
        try {
            if (MoveOutOfBoundsValidator.validateDestination(advance.getDestination())) {
                possibleAdvances.add(advance);
            }
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    protected abstract void setup();

    protected boolean isFirstPlayerPiece() {
        return getPiece().getPlayerType() == PlayerType.FIRST;
    }

    protected Piece getPiece() {
        return piece;
    }

    @Override
    public List<Advance> getPossibleAdvances(Piece piece) {
        this.piece = piece;

        if (!hasSetUp) { // Do initialization once
            setup();
            hasSetUp = true;
        }

        if (!possibleAdvances.isEmpty()) {
            possibleAdvances.clear();
        }
        obtainPossibleAdvances();

        return possibleAdvances;
    }
}
