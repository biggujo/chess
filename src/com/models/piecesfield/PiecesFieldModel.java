package com.models.piecesfield;

import com.controller.AvailableMovesController;
import com.helpers.IndexCalculatorByPoint;
import com.models.currentmove.PlayerStatus;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.moves.Advance;
import com.view.pieces.PieceComponent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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
            tryToMoveTo(coordinates);
            return;
        }

        if (!isSelectingOwnPiece(coordinates)) {
            return;
        }

        if (!hasPieceSelected()) {
            selectPieceAt(coordinates);
            AvailableMovesController.updateAvailableMovesPanel(coordinates);
            return;
        }

        if (hasSelectedTheSamePieceAt(coordinates)) {
            AvailableMovesController.clearAvailableMovesPanel();
            unselectPiece();
            return;
        }

        if (!fieldManager.getField().isEmptyAt(coordinates)) {
            selectAnotherPieceAt(coordinates);
            return;
        }

        tryToMoveTo(coordinates);
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

    private static void selectPieceAt(Point coordinates) {
        enablePieceAt(coordinates);
        fieldManager.getField().get(coordinates).getAdvancesList();
        savePrevCoordinates(coordinates);
    }

    private static void unselectPiece() {
        disablePieceAt(prevCoordinates);
        clearPrevCoordinates();
    }

    private static void selectAnotherPieceAt(Point coordinates) throws IOException {
        disablePieceAt(prevCoordinates);
        enablePieceAt(coordinates);
        AvailableMovesController.updateAvailableMovesPanel(coordinates);

        savePrevCoordinates(coordinates);
    }

    private static void tryToMoveTo(Point point) throws IOException {
        try {
            Advance advance = getAdvanceByDestination(point);
            movePieceTo(advance);
            playerStatus.switchPlayer();
        } catch (IllegalPieceMoveException | NoSuchElementException ignored) {
        } finally {
            AvailableMovesController.clearAvailableMovesPanel();
            unselectPiece();
        }
    }

    private static void movePieceTo(Advance advance) throws IllegalPieceMoveException {
        fieldManager.move(prevCoordinates, advance);
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

    private static Advance getAdvanceByDestination(Point destination) throws NoSuchElementException {
        return fieldManager.getField().get(prevCoordinates).getAdvancesList().getAdvanceByMove(destination);
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
