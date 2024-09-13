package com.view.pieces;

import com.services.iconpathresolvers.IconPaths;
import com.services.iconpathresolvers.IconPathsStorage;
import com.models.pieces.abstractpiece.Piece;

import javax.swing.*;
import java.awt.*;

public class WhitePieceFactory extends PieceComponentFactory {
    public WhitePieceFactory(Dimension dimension) {
        super(dimension, IconPathsStorage.WHITE_PATHS);
    }
}
