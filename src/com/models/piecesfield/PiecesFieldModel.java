package com.models.piecesfield;

import com.controller.AvailableMovesController;
import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.NoMoveHasBeenMadeException;
import com.models.pieces.abstractpiece.Piece;
import com.models.playerstatus.PlayerStatus;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.advanceprocessors.advances.Advance;
import com.services.fieldinitializers.*;
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
    private transient Point prevCoordinates;
    private transient boolean isTheMoveSuccessful;
    private boolean hasMovedAtLeastOnce;
    private boolean isDisabled;

    public static PiecesFieldModel getInstance() {
        if (PiecesFieldModel.instance == null) {
            PiecesFieldModel.instance = new PiecesFieldModel();
        }

        return PiecesFieldModel.instance;
    }

    public static void setInstance(PiecesFieldModel instance) {
        PiecesFieldModel.instance = instance;
    }

    public PiecesFieldModel() {
        List<FieldInitializer> fieldInitializers = new ArrayList<>();
        fieldInitializers.add(new KnightTestFieldInitializer());
        fieldManager = FieldManager.with(fieldInitializers);
        playerStatus = PlayerStatus.fromInitialPlayer(PlayerType.FIRST);
    }

    /**
     * @return captured piece or null
     */
    public Piece captureAt(Point coordinates) throws IOException, NoMoveHasBeenMadeException {
        if (isDisabled) {
            throw new NoMoveHasBeenMadeException();
        }

        if (isRegisteringAnEmptyCellAt(coordinates)) {
            throw new NoMoveHasBeenMadeException();
        }

        if (isReadyToCaptureAt(coordinates)) {
            return tryToMoveTo(coordinates);
        }

        if (!isSelectingOwnPiece(coordinates)) {
            throw new NoMoveHasBeenMadeException();
        }

        if (!hasPieceSelected()) {
            selectPieceAt(coordinates);
            AvailableMovesController.updateAvailableMovesPanel(coordinates);
            throw new NoMoveHasBeenMadeException();
        }

        if (hasSelectedTheSamePieceAt(coordinates)) {
            AvailableMovesController.clearAvailableMovesPanel();
            unselectPiece();
            throw new NoMoveHasBeenMadeException();
        }

        if (!fieldManager.getField().isEmptyAt(coordinates)) {
            selectAnotherPieceAt(coordinates);
            throw new NoMoveHasBeenMadeException();
        }

        return tryToMoveTo(coordinates);
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

    public Piece getCurrentPiece() {
        try {
            return fieldManager.getField().get(prevCoordinates);
        } catch (NullPointerException ignored) {
            return null;
        }
    }

    public Field getField() {
        return fieldManager.getField();
    }

    public FieldManager getFieldManager() {
        return fieldManager;
    }

    public List<JComponent> getComponents() {
        return fieldManager.getComponents().getList();
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public Point getPrevCoordinates() {
        return prevCoordinates;
    }

    public boolean hasMovedAtLeastOnce() {
        return hasMovedAtLeastOnce;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
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

    private Piece tryToMoveTo(Point point) {
        Piece piece = null;
        try {
            piece = fieldManager.getField().get(point);

            Advance advance = getAdvanceByDestination(point);
            movePieceTo(advance);
            hasMovedAtLeastOnce = true;
            playerStatus.switchPlayer();

            return piece;
        } catch (IllegalPieceMoveException | NoSuchElementException ignored) {
        } finally {
            AvailableMovesController.clearAvailableMovesPanel();
            unselectPiece();
        }

        return piece;
    }

    private void movePieceTo(Advance advance) throws IllegalPieceMoveException {
        fieldManager.move(prevCoordinates, advance);
        isTheMoveSuccessful = true;
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
