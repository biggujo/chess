package com.services.iconpathresolvers;

import com.models.cells.CellType;
import com.models.pieces.PieceType;

import java.util.HashMap;
import java.util.Map;

public class IconPathsWhite extends IconPathImpl {
    private static final Map<PieceType, String> PATHS;

    static {
        PATHS = new HashMap<>();

        for (PieceType type : PieceType.values()) {
            addPieceType(PATHS, type, CellType.WHITE);
        }
    }

    @Override
    public String get(PieceType pieceType) {
        return PATHS.get(pieceType);
    }
}
