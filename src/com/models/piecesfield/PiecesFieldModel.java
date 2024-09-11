package com.models.piecesfield;

import com.controller.AvailableMovesController;
import com.helpers.IndexCalculatorByPoint;
import com.models.currentmove.PlayerStatus;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.advanceprocessors.Advance;
import com.services.fieldinitializers.EmptyFieldInitializer;
import com.services.fieldinitializers.FieldInitializer;
import com.view.pieces.PieceComponent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PiecesFieldModel implements Serializable {
    public static PiecesFieldModel instance;
    private final FieldManager fieldManager;
    private final PlayerStatus playerStatus;
    private Point prevCoordinates;
    private boolean hasMoved;

    public static PiecesFieldModel getInstance() {
        if (PiecesFieldModel.instance == null) {
            PiecesFieldModel.instance = new PiecesFieldModel();
        }

        return PiecesFieldModel.instance;
    }

    public static void setInstance(PiecesFieldModel instance) {
        PiecesFieldModel.instance = instance;
    }

    private PiecesFieldModel() {
        List<FieldInitializer> fieldInitializers = new ArrayList<>();
        fieldInitializers.add(new EmptyFieldInitializer());
        fieldManager = FieldManager.with(fieldInitializers);
        playerStatus = PlayerStatus.fromInitialPlayer(PlayerType.FIRST);
    }

    public void registerClickAt(Point coordinates) throws IOException {
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

    public void disablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) fieldManager.getComponents().get(index);
        pieceComponent.setInactive();
    }

    public void enablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) fieldManager.getComponents().get(index);
        pieceComponent.setActive();
    }

    public boolean hasMoved() {
        boolean hasMovedCopy = hasMoved;
        hasMoved = false;
        return hasMovedCopy;
    }

    public Field getField() {
        return fieldManager.getField();
    }

    public void setField(Field field) {
        fieldManager.setField(field);
    }

    public List<JComponent> getComponents() {
        return fieldManager.getComponents().getList();
    }

    private void selectPieceAt(Point coordinates) {
        enablePieceAt(coordinates);
        fieldManager.getField().get(coordinates).getAdvancesList();
        savePrevCoordinates(coordinates);
    }

    private void unselectPiece() {
        disablePieceAt(prevCoordinates);
        clearPrevCoordinates();
    }

    private void selectAnotherPieceAt(Point coordinates) throws IOException {
        disablePieceAt(prevCoordinates);
        enablePieceAt(coordinates);
        AvailableMovesController.updateAvailableMovesPanel(coordinates);

        savePrevCoordinates(coordinates);
    }

    private void tryToMoveTo(Point point) throws IOException {
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

    private void movePieceTo(Advance advance) throws IllegalPieceMoveException {
        fieldManager.move(prevCoordinates, advance);
        hasMoved = true;
    }

    private boolean isReadyToCaptureAt(Point coordinates) {
        return !isSelectingOwnPiece(coordinates) && hasPieceSelected();
    }

    private boolean hasActivatedThePieceBefore() {
        return prevCoordinates != null;
    }

    private boolean isRegisteringAnEmptyCellAt(Point coordinates) {
        return !hasActivatedThePieceBefore() && isCellEmptyAt(coordinates);
    }

    private void savePrevCoordinates(Point coordinates) {
        prevCoordinates = coordinates;
    }

    private void clearPrevCoordinates() {
        prevCoordinates = null;
    }

    private boolean hasPieceSelected() {
        return hasActivatedThePieceBefore();
    }

    private boolean isCellEmptyAt(Point coordinates) {
        return fieldManager.getField().get(coordinates).getPieceType() == PieceType.EMPTY;
    }

    private boolean hasSelectedTheSamePieceAt(Point coordinates) {
        return prevCoordinates.equals(coordinates);
    }

    private Advance getAdvanceByDestination(Point destination) throws NoSuchElementException {
        return fieldManager.getField().get(prevCoordinates).getAdvancesList().getAdvanceByMove(destination);
    }

    private boolean isSelectingOwnPiece(Point coordinates) {
        PlayerType piecePlayerType = fieldManager.getField().get(coordinates).getPlayerType();

        if (piecePlayerType.equals(PlayerType.NONE)) {
            return true;
        }

        return fieldManager.getField().get(coordinates).getPlayerType()
                .equals(playerStatus.getCurrentPlayer());
    }
}
