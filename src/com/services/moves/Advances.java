package com.services.moves;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Advance getAdvanceByMove(Point point) throws NoSuchElementException {
        Optional<Advance> advance = availableAdvances.stream().filter(a -> a.getDestination().equals(point)).findFirst();

        if (advance.isEmpty()) {
            throw new NoSuchElementException();
        }

        return advance.get();
    }

    public boolean isAdvancePresent(Point dest) {
        return getAdvanceByMove(dest) != null;
    }

    @Override
    public String toString() {
        return "Advances{" +
                "availableAdvances=" + availableAdvances +
                '}';
    }
}
