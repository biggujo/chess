package com.services.moves;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Advances {
    private final List<Advance> availableAdvances;

    public Advances() {
        availableAdvances = new ArrayList<>();
    }

    public List<Advance> getAvailableAdvances() {
        return availableAdvances;
    }

    public List<Point> getAvailableMoves() {
        return availableAdvances.stream().map(Advance::getDestination).toList();
    }

    @Override
    public String toString() {
        return "Advances{" +
                "availableAdvances=" + availableAdvances +
                '}';
    }
}
