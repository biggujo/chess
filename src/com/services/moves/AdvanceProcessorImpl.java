package com.services.moves;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.Piece;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.validators.MoveOutOfBoundsValidator;

import java.util.ArrayList;
import java.util.List;

abstract public class AdvanceProcessorImpl implements AdvanceProcessor {
    private final Piece piece;
    private final Field field;
    private final List<Advance> possibleAdvances;

    public AdvanceProcessorImpl(Piece piece) {
        this.piece = piece;
        this.field = PiecesFieldModel.getField();
        this.possibleAdvances = new ArrayList<>();
    }

    abstract protected void obtainPossibleAdvances();

    protected void add(Advance advance) {
        try {
            if (MoveOutOfBoundsValidator.validateDestination(advance.getDestination())) {
                possibleAdvances.add(advance);
            }
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    protected Piece getPiece() {
        return piece;
    }

    protected Field getField() {
        return field;
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
