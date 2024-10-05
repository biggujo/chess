package com.view.pieces;

import com.services.iconpathresolvers.IconPathsStorage;

public class WhitePieceFactory extends PieceComponentFactory {
    public WhitePieceFactory() {
        super(IconPathsStorage.WHITE_PATHS);
    }
}
