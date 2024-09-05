package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.view.pieces.PieceComponent;
import com.models.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesFieldModel {
    private static final FieldManager FIELD_TYPES_MANAGER = new FieldManager();
    private static final Components COMPONENTS = Components.fromField(FIELD_TYPES_MANAGER.getField());
    private static Point prevCoordinates;
    private static boolean hasMoved;

    public static void registerClickAt(Point coordinates) {
        if (isRegisteringAnEmptyCellAt(coordinates)) {
            return;
        }

        if (!hasPieceSelected()) {
            selectPieceAt(coordinates);
            return;
        }

        movePieceTo(coordinates);
        unselectPiece();
        hasMoved = true;
    }

    private static void selectPieceAt(Point coordinates) {
        enablePieceAt(coordinates);
        savePrevCoordinates(coordinates);
    }

    private static void unselectPiece() {
        disablePieceAt(prevCoordinates);
        clearPrevCoordinates();
    }

    public static void disablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) COMPONENTS.get(index);
        pieceComponent.setInactive();
    }

    public static void enablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) COMPONENTS.get(index);
        pieceComponent.setActive();
    }

    private static void movePieceTo(Point coordinates) {
        int srcIndex = IndexCalculatorByPoint.getIndex(prevCoordinates);
        int destIndex = IndexCalculatorByPoint.getIndex(coordinates);

        FIELD_TYPES_MANAGER.getField().get(coordinates).moveTo(coordinates);
        COMPONENTS.swap(srcIndex, destIndex);
    }

    public static List<JComponent> getField() {
        return COMPONENTS.getComponents();
    }

    public static boolean hasMoved() {
        boolean hasMovedCopy = hasMoved;
        hasMoved = false;
        return hasMovedCopy;
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

    private static boolean hasPieceSelected() {
        return hasActivatedThePieceBefore();
    }

    private static boolean isCellEmptyAt(Point coordinates) {
        return FIELD_TYPES_MANAGER.getField().get(coordinates).getType() == PieceType.EMPTY;
    }
}
