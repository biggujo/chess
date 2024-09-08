package com.models.pieces;

import com.helpers.IndexCalculatorByPoint;
import com.services.moves.AdvanceProcessor;
import com.services.moves.Advances;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract class PieceImpl implements Piece {
    private final Advances availableAdvances;
    private List<AdvanceProcessor> advanceProcessors;
    private Point coordinates;
    private boolean hasEmptiedMoves;
    private boolean hasBeenMoved;

    PieceImpl(Point coordinates) {
        this.availableAdvances = new Advances();
        this.advanceProcessors = new ArrayList<>();
        this.coordinates = coordinates;
        this.hasEmptiedMoves = true;
    }

    public void moveTo(Point point) throws ArrayIndexOutOfBoundsException {
        if (!isMoveLegalTo(point)) {
            throw new IllegalPieceMoveException();
        }

        setCoordinates(point);
        clearAvailableMoves();
        hasEmptiedMoves = true;
        hasBeenMoved = true;
    }

    protected abstract void addPossibleAdvances();

    void clearAvailableMoves() {
        if (availableAdvances.getAvailableAdvances().isEmpty()) {
            return;
        }

        availableAdvances.getAvailableAdvances().clear();
    }

    @Override
    public Advances getAdvancesList() {
        if (hasEmptiedMoves) {
            hasEmptiedMoves = false;
            addPossibleAdvances();
        }

        System.out.println(availableAdvances);

        return availableAdvances;
    }

    public List<AdvanceProcessor> getAdvanceProcessors() {
        return advanceProcessors;
    }

    @Override
    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public boolean hasBeenMoved() {
        return hasBeenMoved;
    }

    protected boolean hasAddedAdvanceProcessors() {
        return !advanceProcessors.isEmpty();
    }

    @Override
    public int compareTo(Piece o) {
        if (this == o) {
            return 0;
        }

        Point thisPoint = this.getCoordinates();
        Point otherPoint = this.getCoordinates();

        int thisPointIndex = IndexCalculatorByPoint.getIndex(thisPoint);
        int otherPointIndex = IndexCalculatorByPoint.getIndex(otherPoint);

        return Integer.compare(thisPointIndex, otherPointIndex);
    }

    private boolean isMoveLegalTo(Point givenCoordinates) {
        return getAdvancesList().getAvailableMoves().stream().anyMatch(p -> p.equals(givenCoordinates));
    }
}
