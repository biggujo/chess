package com.view.pieces;

import com.services.iconpathresolvers.IconPaths;
import com.models.pieces.abstractpiece.Piece;

import javax.swing.*;
import java.awt.*;

public abstract class PieceComponentFactory {
    private final Dimension dimension;
    private final IconPaths paths;

    PieceComponentFactory(Dimension dimension, IconPaths paths) {
        this.dimension = dimension;
        this.paths = paths;
    }

    public JComponent getInstance(Piece piece) {
        String iconPath = paths.get(piece.getPieceType());
        return new PieceComponent(dimension, iconPath);
    }
}
