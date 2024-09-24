package com.view.pieces;

import com.services.iconpathresolvers.IconPaths;
import com.models.pieces.abstractpiece.Piece;

import javax.swing.*;
import java.awt.*;

public abstract class PieceComponentFactory {
    private final IconPaths paths;

    PieceComponentFactory(IconPaths paths) {
        this.paths = paths;
    }

    public JComponent getInstance(Dimension dimension, Piece piece) {
        String iconPath = paths.get(piece.getPieceType());
        return new PieceComponent(dimension, iconPath);
    }
}
