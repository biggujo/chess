package com.view.pieces;

import com.models.pieces.PieceType;
import com.services.iconpathresolvers.IconPaths;

import javax.swing.*;
import java.awt.*;

public abstract class PieceComponentFactory {
    private final IconPaths paths;

    PieceComponentFactory(IconPaths paths) {
        this.paths = paths;
    }

    public JComponent getInstance(Dimension dimension, PieceType pieceType) {
        String iconPath = paths.get(pieceType);
        return new PieceComponent(dimension, iconPath);
    }
}
