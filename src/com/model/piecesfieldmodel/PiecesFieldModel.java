package com.model.piecesfieldmodel;

import com.globals.Defaults;
import com.view.pieces.PieceFactory;
import com.view.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesFieldModel {
    private static PiecesFieldModel instance;

    private final FieldTypes fieldTypes = new FieldTypes();
    private final FieldComponents fieldComponents = new FieldComponents();

    public static PiecesFieldModel getInstance() {
        if (PiecesFieldModel.instance == null) {
            PiecesFieldModel.instance = new PiecesFieldModel();
        }

        return PiecesFieldModel.instance;
    }

    private PiecesFieldModel() {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                PieceType currentType = fieldTypes.getFieldTypes()[i][j];

                fieldComponents.add(PieceFactory.getInstance(currentType));
            }
        }
    }

    public void movePiece(Point src, Point dest) {
        if (src.equals(dest)) {
            return;
        }

        int srcIndex = getIndexByPoint(src);
        int destIndex = getIndexByPoint(dest);

        fieldTypes.swap(src, dest);
        fieldComponents.swap(srcIndex, destIndex);
    }

    public List<JComponent> getFieldComponents() {
        return fieldComponents.getFieldComponents();
    }

    public PieceType getPieceTypeAt(Point coordinates) {
        return fieldTypes.getPieceTypeAt(coordinates);
    }

    private int getIndexByPoint(Point point) {
        return Defaults.TILE_AMOUNT * point.y + point.x;
    }
}
