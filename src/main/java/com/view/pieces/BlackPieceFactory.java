package com.view.pieces;

import com.services.iconpathresolvers.IconPathsStorage;

public class BlackPieceFactory extends PieceComponentFactory {
    public BlackPieceFactory() {
        super(IconPathsStorage.BLACK_PATHS);
    }
}
