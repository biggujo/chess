package com.services.moves;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.validators.MoveOutOfBoundsValidator;

import java.util.ArrayList;
import java.util.List;

abstract public class AdvanceProcessorImpl implements AdvanceProcessor {
    private final Piece piece;
    private final List<Advance> possibleAdvances;
    private final List<Runnable> methodsToRun;

    public AdvanceProcessorImpl(Piece piece) {
        this.piece = piece;
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

    protected boolean isFirstPlayerPiece() {
        return getPiece().getPlayerType() == PlayerType.FIRST;
    }

    protected Piece getPiece() {
        return piece;
    }

    @Override
    public List<Advance> getPossibleAdvances() {
        if (!possibleAdvances.isEmpty()) {
            possibleAdvances.clear();
        }
        obtainPossibleAdvances();

        return possibleAdvances;
    }
}
