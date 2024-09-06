package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.IllegalPieceMoveException;
import com.view.pieces.PieceComponent;
import com.models.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiecesFieldModel {
    private static final FieldManager fieldManager = new FieldManager();
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

        try {
            movePieceTo(coordinates);
            hasMoved = true;
        } catch (IllegalPieceMoveException ignored) {
        } finally {
            unselectPiece();
        }
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
        PieceComponent pieceComponent = (PieceComponent) fieldManager.getComponents().get(index);
        pieceComponent.setInactive();
    }

    public static void enablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) fieldManager.getComponents().get(index);
        pieceComponent.setActive();
    }

    public static boolean hasMoved() {
        boolean hasMovedCopy = hasMoved;
        hasMoved = false;
        return hasMovedCopy;
    }

    private static void movePieceTo(Point coordinates) throws IllegalPieceMoveException {
        fieldManager.move(prevCoordinates, coordinates);
    }

    public static List<JComponent> getComponents() {
        return fieldManager.getComponents().getList();
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
        return fieldManager.getField().get(coordinates).getType() == PieceType.EMPTY;
    }
}
