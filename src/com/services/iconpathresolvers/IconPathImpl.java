package com.services.iconpathresolvers;

import com.models.cells.CellType;
import com.models.pieces.PieceType;

import java.io.File;
import java.util.Map;

public abstract class IconPathImpl implements IconPaths {
    protected static void addPieceType(Map<PieceType, String> map, PieceType pieceType, CellType color) {
        map.put(pieceType, createPathFor(pieceType, color));
    }

    protected static String createPathFor(PieceType pieceType, CellType type) {
        return "assets" + File.separator + pieceType.name().toLowerCase() + "-" + type.name() + ".svg";
    }
}
