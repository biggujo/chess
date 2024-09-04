package com.model.piecesfieldmodel;

import com.globals.Defaults;
import com.view.pieces.Piece;
import com.view.pieces.PieceFactory;
import com.view.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesFieldModel {
    private static final FieldTypes fieldTypes = new FieldTypes();
    private static final FieldComponents fieldComponents = new FieldComponents();
    private static Point prevCoordinates;
    private static boolean hasMoved;

    static {
        for (int i = 0; i < Defaults.TILE_AMOUNT; i++) {
            for (int j = 0; j < Defaults.TILE_AMOUNT; j++) {
                PieceType currentType = fieldTypes.getFieldTypes()[i][j];

                fieldComponents.add(PieceFactory.getInstance(currentType));
            }
        }
    }

    public static void registerClickAt(Point coordinates) {
        if (isRegisteringAnEmptyCellAt(coordinates)) {
            return;
        }

        if (!isReadyToMove()) {
            enablePieceAt(coordinates);
            savePrevCoordinates(coordinates);
            return;
        }

        disablePieceAt(prevCoordinates);
        movePieceTo(coordinates);
        clearPrevCoordinates();

        hasMoved = true;
    }

    public static void disablePieceAt(Point coordinates) {
        int index = getIndexByPoint(coordinates);
        Piece piece = (Piece) fieldComponents.get(index);
        piece.setInactive();
    }

    public static void enablePieceAt(Point coordinates) {
        int index = getIndexByPoint(coordinates);
        Piece piece = (Piece) fieldComponents.get(index);
        piece.setActive();
    }

    private static void movePieceTo(Point coordinates) {
        int srcIndex = PiecesFieldModel.getIndexByPoint(prevCoordinates);
        int destIndex = PiecesFieldModel.getIndexByPoint(coordinates);

        fieldTypes.swap(prevCoordinates, coordinates);
        fieldComponents.swap(srcIndex, destIndex);
    }

    public static List<JComponent> getField() {
        return fieldComponents.getFieldComponents();
    }

    public static boolean hasMoved() {
        boolean hasMovedCopy = hasMoved;
        hasMoved = false;
        return hasMovedCopy;
    }

    private static int getIndexByPoint(Point point) {
        return Defaults.TILE_AMOUNT * point.y + point.x;
    }

    private static boolean hasActivatedThePieceBefore() {
        return prevCoordinates != null;
    }

    private static boolean isRegisteringAnEmptyCellAt(Point coordinates) {
        return !hasActivatedThePieceBefore() && isCellEmptyAt(coordinates);
    }

    private static void savePrevCoordinates(Point coordinates) {
        prevCoordinates = coordinates;
    }

    private static void clearPrevCoordinates() {
        prevCoordinates = null;
    }

    private static boolean isReadyToMove() {
        return hasActivatedThePieceBefore();
    }

    private static boolean isCellEmptyAt(Point coordinates) {
        return fieldTypes.getPieceTypeAt(coordinates) == PieceType.EMPTY;
    }
}
