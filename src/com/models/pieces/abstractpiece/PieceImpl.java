package com.models.pieces.abstractpiece;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.services.moves.Advance;
import com.services.moves.AdvanceProcessor;
import com.services.moves.AdvanceProcessors;
import com.services.moves.Advances;

import java.awt.*;
import java.util.List;

abstract public class PieceImpl implements Piece {
    private final Advances advances;
    private AdvanceProcessors advanceProcessors;
    private final Status status;
    private final PlayerType playerType;

    protected PieceImpl(PlayerType playerType, Point coordinates) {
        this.status = new Status(coordinates);
        this.advances = new Advances();
        this.playerType = playerType;
    }

    public void moveTo(Point point) throws ArrayIndexOutOfBoundsException {
        if (!isMoveLegalTo(point)) {
            throw new IllegalPieceMoveException();
        }

        revalidatePossibleAdvances();
        status.setCoordinates(point);
        status.setEmptiedMoves(true);
        status.setMoved(true);
    }

    private void revalidatePossibleAdvances() {
        clearPossibleAdvances();
        addPossibleAdvances();
    }

    protected void addPossibleAdvances() {
        for (AdvanceProcessor c : advanceProcessors.getAdvanceProcessors()) {
            List<Advance> newPossibleAdvances = c.getPossibleAdvances();
            getAdvancesList().getAvailableAdvances().addAll(newPossibleAdvances);
        }
    }

    void clearPossibleAdvances() {
        if (advances.getAvailableAdvances().isEmpty()) {
            return;
        }

        advances.getAvailableAdvances().clear();
    }

    @Override
    public Advances getAdvancesList() {
        if (status.isEmptiedMoves()) {
            status.setEmptiedMoves(false);
            addPossibleAdvances();
        }

        System.out.println(advances);

        return advances;
    }

    public void setAdvanceProcessors(AdvanceProcessors advanceProcessors) {
        this.advanceProcessors = advanceProcessors;
    }

    public AdvanceProcessors getAdvanceProcessors() {
        return advanceProcessors;
    }

    public Advances getAdvances() {
        return advances;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    protected boolean hasAddedAdvanceProcessors() {
        return !advanceProcessors.getAdvanceProcessors().isEmpty();
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public int compareTo(Piece o) {
        if (this == o) {
            return 0;
        }

        Point thisPoint = status.getCoordinates();
        Point otherPoint = status.getCoordinates();

        int thisPointIndex = IndexCalculatorByPoint.getIndex(thisPoint);
        int otherPointIndex = IndexCalculatorByPoint.getIndex(otherPoint);

        return Integer.compare(thisPointIndex, otherPointIndex);
    }

    private boolean isMoveLegalTo(Point givenCoordinates) {
        return getAdvancesList().getAvailableMoves().stream().anyMatch(p -> p.equals(givenCoordinates));
    }
}
