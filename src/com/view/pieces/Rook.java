package com.view.pieces;

import com.helpers.IconPaths;
import com.helpers.PiecesIconLoader;

import java.awt.*;

final public class Rook extends PieceComponent {
    public Rook(Dimension dimension, IconPaths iconPath) {
        super(dimension, PiecesIconLoader.fetchImageFrom(iconPath));
    }
}
