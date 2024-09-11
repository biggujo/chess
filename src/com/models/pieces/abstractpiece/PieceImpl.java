package com.models.pieces.abstractpiece;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.models.pieces.concretes.PawnModel;
import com.services.advanceprocessors.Advance;
import com.services.advanceprocessors.AdvanceProcessor;
import com.services.advanceprocessors.AdvanceProcessors;
import com.services.advanceprocessors.Advances;

import java.awt.*;
import java.io.*;
import java.util.List;

abstract public class PieceImpl implements Piece, Serializable {
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
            List<Advance> newPossibleAdvances = c.getPossibleAdvances(this);
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
        return getAdvancesList().isAdvancePresent(givenCoordinates);
    }

    protected void writeObjectCommon(ObjectOutputStream stream) throws IOException {
        stream.writeObject(getStatus());
        stream.writeObject(getPlayerType());
    }

    protected void readObjectCommon(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        setStatus((Status) stream.readObject());
        setPlayerType((PlayerType) stream.readObject());
        setAdvances(new Advances());
    }
}
