package com.services.advanceprocessors.advances;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Advances {
    private final List<Advance> availableAdvances;

    public Advances() {
        this(new ArrayList<>());
    }

    public Advances(List<Advance> initialAdvances) {
        this.availableAdvances = new ArrayList<>(initialAdvances);
    }

    public List<Advance> getAvailableAdvances() {
        return availableAdvances;
    }

    public List<Point> getAvailableMoves() {
        return availableAdvances.stream().map(Advance::getDestination).toList();
    }

    public List<Point> getAvailableCaptures() {
        return availableAdvances.stream().map(Advance::getPossibleCapture).toList();
    }

    public Advance getAdvanceByMove(Point point) throws NoSuchElementException {
        Optional<Advance> advance = availableAdvances.stream().filter(a -> a.getDestination().equals(point)).findFirst();

        if (advance.isEmpty()) {
            throw new NoSuchElementException();
        }

        return advance.get();
    }

    public boolean isAdvancePresent(Point dest) {
        try {
            getAdvanceByMove(dest);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Advances{" +
                "availableAdvances=" + availableAdvances +
                '}';
    }
}
