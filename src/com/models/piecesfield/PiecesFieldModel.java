package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.currentmove.PlayerStatus;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.Piece;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.view.panels.PanelManager;
import com.view.pieces.PieceComponent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PiecesFieldModel {
    private static final FieldManager fieldManager = new FieldManager();
    private static final PlayerStatus playerStatus = PlayerStatus.fromInitialPlayer(PlayerType.FIRST);
    private static Point prevCoordinates;
    private static boolean hasMoved;

    public static void registerClickAt(Point coordinates) throws IOException {
        if (isRegisteringAnEmptyCellAt(coordinates)) {
            return;
        }

        if (isReadyToCaptureAt(coordinates)) {
            tryToCaptureAt(coordinates);
            return;
        }

        if (!isSelectingOwnPiece(coordinates)) {
            return;
        }

        if (!hasPieceSelected()) {
            selectPieceAt(coordinates);
            updateAvailableMovesPanel(coordinates);
            return;
        }

        if (hasSelectedTheSamePieceAt(coordinates)) {
            clearAvailableMovesPanel();
            unselectPiece();
            return;
        }

        if (!fieldManager.getField().isEmptyAt(coordinates)) {
            selectAnotherPieceAt(coordinates);
            return;
        }

        tryToMoveTo(coordinates);
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

    public static Field getField() {
        return fieldManager.getField();
    }

    public static List<JComponent> getComponents() {
        return fieldManager.getComponents().getList();
    }

    private static void selectAnotherPieceAt(Point coordinates) throws IOException {
        disablePieceAt(prevCoordinates);
        enablePieceAt(coordinates);
        updateAvailableMovesPanel(coordinates);

        savePrevCoordinates(coordinates);
    }

    private static void tryToCaptureAt(Point coordinates) throws IOException {
        try {
            fieldManager.getField().captureAt(coordinates);
            tryToMoveTo(coordinates);
        } catch (IllegalPieceMoveException ignored) {
        }
    }

    private static void tryToMoveTo(Point coordinates) throws IOException {
        try {
            movePieceTo(coordinates);
            playerStatus.switchPlayer();
        } catch (IllegalPieceMoveException ignored) {
        } finally {
            clearAvailableMovesPanel();
            unselectPiece();
        }
    }

    private static void movePieceTo(Point coordinates) throws IllegalPieceMoveException {
        fieldManager.move(prevCoordinates, coordinates);
        hasMoved = true;
    }

    private static boolean isReadyToCaptureAt(Point coordinates) {
        return !isSelectingOwnPiece(coordinates) && hasPieceSelected();
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
        return fieldManager.getField().get(coordinates).getPieceType() == PieceType.EMPTY;
    }

    private static boolean hasSelectedTheSamePieceAt(Point coordinates) {
        return prevCoordinates.equals(coordinates);
    }

    private static void updateAvailableMovesPanel(Point coordinates) throws IOException {
        Piece piece = PiecesFieldModel.getField().get(coordinates);
        List<Point> availableMoves = piece.getAvailableMoves();
        PanelManager.getInstance().getAvailableMovesPanel().setAvailableMoves(availableMoves);
    }

    private static void clearAvailableMovesPanel() throws IOException {
        PanelManager.getInstance().getAvailableMovesPanel().setAvailableMoves(new ArrayList<>());
    }

    private static boolean isSelectingOwnPiece(Point coordinates) {
        PlayerType piecePlayerType = fieldManager.getField().get(coordinates).getPlayerType();

        if (piecePlayerType.equals(PlayerType.NONE)) {
            return true;
        }

        return fieldManager.getField().get(coordinates).getPlayerType()
                .equals(playerStatus.getCurrentPlayer());
    }
}
