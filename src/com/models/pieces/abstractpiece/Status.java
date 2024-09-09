package com.models.pieces.abstractpiece;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.validators.MoveOutOfBoundsValidator;

import java.awt.*;

public class Status {
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
