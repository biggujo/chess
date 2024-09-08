package com.services.moves;

import java.awt.*;

public class Advance {
    private final Point destination;
    private final Point possibleCapture;

    public Advance(Point destination, Point possibleCapture) {
        this.destination = destination;
        this.possibleCapture = possibleCapture;
    }

    public Point getDestination() {
        return destination;
    }

    public Point getPossibleCapture() {
        return possibleCapture;
    }

    @Override
    public String toString() {
        return "Advance{" +
                "destination=" + destination +
                ", possibleCapture=" + possibleCapture +
                '}';
    }
}
