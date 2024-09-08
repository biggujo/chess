package com.view.pieces;

import com.globals.Defaults;
import com.models.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class PieceComponentFactory {
    private static final Dimension dimension = new Dimension(Defaults.PIECE_SIZE, Defaults.PIECE_SIZE);
    private static final BlackPieceFactory blackPieceFactory = new BlackPieceFactory(dimension);
    private static final WhitePieceFactory whitePieceFactory = new WhitePieceFactory(dimension);

    public static JComponent getInstance(Piece piece) {
        return switch (piece.getPlayerType()) {
            case FIRST -> whitePieceFactory.getInstance(piece);
            case SECOND -> blackPieceFactory.getInstance(piece);
            case NONE -> new EmptyPieceComponent(dimension);
        };
    }
}
