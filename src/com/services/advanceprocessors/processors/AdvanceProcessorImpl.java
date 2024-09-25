package com.services.advanceprocessors.processors;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.services.advanceprocessors.advances.Advance;
import com.validators.MoveOutOfBoundsValidator;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Advance> getPossibleAdvances(Piece piece) {
        this.piece = piece;

        if (!hasSetUp) { // Do initialization once
            setupOnce();
            hasSetUp = true;
        }

        if (!possibleAdvances.isEmpty()) {
            possibleAdvances.clear();
        }
        obtainPossibleAdvances();

        return possibleAdvances;
    }

    protected abstract List<Runnable> getMethodsToRun(List<Runnable> methodsToRun);

    protected abstract void setupOnce();

    protected boolean isFirstPlayerPiece() {
        return getPiece().getPlayerType() == PlayerType.FIRST;
    }

    protected Piece getPiece() {
        return piece;
    }

    protected boolean isBusyCellAt(Point coordinates) throws IndexOutOfBoundsException {
        return !PiecesFieldModel.getInstance().getField().isEmptyAt(coordinates);
    }

    protected boolean isPieceFromTheSamePlayerAt(Point coordinates) {
        return PiecesFieldModel.getInstance().getField().get(coordinates).getPlayerType() == getPiece().getPlayerType();
    }

    /**
     * @return is move added without capture
     */
    protected boolean addMove(Point newCoordinates) {
        if (isBusyCellAt(newCoordinates) || isPieceFromTheSamePlayerAt(newCoordinates)) {
            addAdvanceToWithCapture(newCoordinates, newCoordinates);
            return false;
        }

        addAdvanceTo(newCoordinates);
        return true;
    }

    protected boolean addMove(int newX, int newY) {
        Point newCoordinates = new Point(newX, newY);
        return addMove(newCoordinates);
    }

    protected void addAdvanceToWithCapture(Point newCoordinates, Point captureCoordinates) {
        Advance advance = new Advance(newCoordinates, captureCoordinates);
        add(advance);
    }

    protected void addAdvanceTo(Point newCoordinates) {
        Advance advance = new Advance(newCoordinates, null);
        add(advance);
    }

    private void obtainPossibleAdvances() {
        methodsToRun.forEach((m) -> {
            try {
                m.run();
            } catch (IllegalPieceMoveException ignored) {
            }
        });
    }

    private void add(Advance advance) {
        try {
            if (MoveOutOfBoundsValidator.validateDestination(advance.getDestination())) {
                possibleAdvances.add(advance);
            }
        } catch (IllegalPieceMoveException ignored) {
        }
    }
}
