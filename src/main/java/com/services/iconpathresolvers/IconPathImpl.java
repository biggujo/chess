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
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("src");
        stringBuilder.append(File.separator);

        stringBuilder.append("main");
        stringBuilder.append(File.separator);

        stringBuilder.append("resources");
        stringBuilder.append(File.separator);

        stringBuilder.append(pieceType.name().toLowerCase());
        stringBuilder.append("-");
        stringBuilder.append(type.name());
        stringBuilder.append(".svg");

        return stringBuilder.toString();
    }
}
