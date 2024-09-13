package com.view.pieces;

import com.globals.Defaults;

import java.awt.*;

public class EmptyPieceComponent extends PieceComponent {
    public EmptyPieceComponent() {
        super(new Dimension(Defaults.PIECE_SIZE, Defaults.PIECE_SIZE), "assets/empty.svg");
    }
}
