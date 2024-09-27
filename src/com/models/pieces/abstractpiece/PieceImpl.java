package com.models.pieces.abstractpiece;

import com.globals.Defaults;
import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.models.piecesfield.Field;
import com.services.advanceprocessors.advances.Advance;
import com.services.advanceprocessors.processors.AdvanceProcessor;
import com.services.advanceprocessors.processorlists.AdvanceProcessors;
import com.services.advanceprocessors.advances.Advances;

import java.awt.*;
import java.io.*;
import java.util.List;

abstract public class PieceImpl implements Piece {
    @Serial
    private static final long serialVersionUID = 1L;

    private transient Advances advances;
    private transient AdvanceProcessors advanceProcessors;
    private Status status;
    private PlayerType playerType;

    protected PieceImpl(PlayerType playerType, Point coordinates, AdvanceProcessors advanceProcessors) {
        this.status = new Status(coordinates);
        this.advances = new Advances();
        this.advanceProcessors = advanceProcessors;
        this.playerType = playerType;
    }

    public void moveTo(Point point) throws IllegalPieceMoveException {
        if (!isMoveLegalTo(point)) {
            throw new IllegalPieceMoveException();
        }

        status.setCoordinates(point);
        status.setEmptiedMoves(true);
        status.setMoved(true);
        clearPossibleAdvances();
    }

    public void revalidatePossibleAdvancesWith(Field field) {
        status.setEmptiedMoves(false);

        clearPossibleAdvances();
        addPossibleAdvances(field);
    }

    protected void addPossibleAdvances(Field field) {
        for (AdvanceProcessor c : advanceProcessors.getAdvanceProcessors()) {
            List<Advance> newPossibleAdvances = c.getPossibleAdvances(field, this);
            advances.getAvailableAdvances().addAll(newPossibleAdvances);
        }
    }

    protected void clearPossibleAdvances() {
        if (advances.getAvailableAdvances().isEmpty()) {
            return;
        }

        advances.getAvailableAdvances().clear();
        status.setEmptiedMoves(true);
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public Advances getPossibleAdvances() {
        return advances;
    }

    @Override
    public void setPossibleAdvances(Advances advances) {
        this.advances = advances;
    }

    public boolean hasReachedTheTop() {
        if (isFirstPlayer()) {
            return getStatus().getCoordinates().y == 0;
        }

        return getStatus().getCoordinates().y == Defaults.TILE_AMOUNT - 1;
    }

    protected void setAdvances(Advances advances) {
        this.advances = advances;
    }

    protected void setAdvanceProcessors(AdvanceProcessors advanceProcessors) {
        this.advanceProcessors = advanceProcessors;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    protected void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
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
        return advances.isAdvancePresent(givenCoordinates);
    }

    protected void writeObjectCommon(ObjectOutputStream stream) throws IOException {
        stream.writeObject(getStatus());
        stream.writeObject(getPlayerType());
    }

    protected void readObjectCommon(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        setStatus((Status) stream.readObject());
        setPlayerType((PlayerType) stream.readObject());
        status.setEmptiedMoves(true);
        setAdvances(new Advances());
    }

    private boolean isFirstPlayer() {
        return getPlayerType() == PlayerType.FIRST;
    }
}
