package com.models.pieces;

import com.validators.MoveValidator;
import com.view.pieces.PieceType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PieceModelImpl implements PieceModel {
    private PieceType[][] field;
    private List<Point> availableMoves;
    private Point coordinates;

    public PieceModelImpl(PieceType[][] field, Point coordinates) {
        this.coordinates = coordinates;
        this.field = field;

        availableMoves = new ArrayList<>();
        addAvailableMoves();
    }

    @Override
    public void moveTo(Point point) throws ArrayIndexOutOfBoundsException {
        setCoordinates(point);
        clearAvailableMoves();
        addAvailableMoves();
    }

    @Override
    public List<Point> getAvailableMoves() {
        return availableMoves;
    }

    public void setCoordinates(Point coordinates) throws ArrayIndexOutOfBoundsException {
        if (MoveValidator.validate(coordinates)) {
            this.coordinates = coordinates;
        }
    }

    public Point getCoordinates() {
        return coordinates;
    }

    abstract void addAvailableMoves();

    private void clearAvailableMoves() {
        availableMoves = new ArrayList<>();
    }
}
