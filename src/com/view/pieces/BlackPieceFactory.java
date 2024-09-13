package com.view.pieces;

import com.services.iconpathresolvers.IconPathsStorage;
import com.models.pieces.PieceFactory;
import com.models.pieces.PieceType;

import java.awt.*;
import java.util.Map;

public class BlackPieceFactory extends PieceComponentFactory {
    public BlackPieceFactory(Dimension dimension) {
        super(dimension, IconPathsStorage.BLACK_PATHS);
    }
}
