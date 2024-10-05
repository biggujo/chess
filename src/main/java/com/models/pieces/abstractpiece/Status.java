package com.models.pieces.abstractpiece;

import com.models.pieces.IllegalPieceMoveException;
import com.validators.MoveOutOfBoundsValidator;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Status implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean isEmptiedMoves;
    private boolean isMoved;
    private Point coordinates;

    public Status(Point coordinates) {
        this.coordinates = coordinates;
        isEmptiedMoves = true; // No moves by default
    }

    public boolean isEmptiedMoves() {
        return isEmptiedMoves;
    }

    public void setEmptiedMoves(boolean emptiedMoves) {
        isEmptiedMoves = emptiedMoves;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) throws IllegalPieceMoveException {
        if (MoveOutOfBoundsValidator.validateDestination(coordinates)) {
            this.coordinates = coordinates;
        }
    }
}
