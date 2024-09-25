package com.services.fieldlisteners;

import com.models.pieces.abstractpiece.Piece;

import java.util.ArrayList;
import java.util.List;

public class FieldListeners {
    private final List<FieldListener> listeners;

    public FieldListeners() {
        listeners = new ArrayList<>();
    }

    public void add(FieldListener listener) {
        listeners.add(listener);
    }

    public void runAll(Piece currentPiece, Piece capturedPiece) {
        listeners.forEach(l -> l.onMoveEnds(currentPiece, capturedPiece));
    }
}
