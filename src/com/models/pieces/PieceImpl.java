package com.models.pieces;

import com.helpers.IndexCalculatorByPoint;

import java.awt.*;
import java.util.List;

abstract class PieceImpl implements Piece {
    private final List<Point> availableMoves;
    private Point coordinates;

    PieceImpl(Point coordinates, List<Point> availableMoves) {
        this.coordinates = coordinates;
        this.availableMoves = availableMoves;
    }

    public void moveTo(Point point) throws ArrayIndexOutOfBoundsException {
        setCoordinates(point);
    }

    void clearAvailableMoves() {
        if (availableMoves.isEmpty()) {
            return;
        }

        availableMoves.clear();
    }

    @Override
    public List<Point> getAvailableMoves() {
        return availableMoves;
    }

    @Override
    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
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
}
