package com.view.pieces;

import com.helpers.IconPaths;
import com.helpers.PiecesIconLoader;

import java.awt.*;

final class Pawn extends PieceComponent {
    public Pawn(Dimension dimension, IconPaths iconPath) {
        super(dimension, PiecesIconLoader.fetchImageFrom(iconPath));
    }
}
