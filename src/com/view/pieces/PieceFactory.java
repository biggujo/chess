package com.view.pieces;

import com.globals.Defaults;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PieceFactory {
    private static final Dimension dimension = new Dimension(Defaults.PIECE_SIZE, Defaults.PIECE_SIZE);

    public static JComponent getInstance(PieceType type) {
        return switch (type) {
            case PAWN -> new Pawn(dimension);
            case EMPTY -> new EmptyPiece(dimension);
        };
    }
}
