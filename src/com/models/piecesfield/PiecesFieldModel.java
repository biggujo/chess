package com.models.piecesfield;

import com.models.pieces.abstractpiece.Piece;
import com.models.playerstatus.PlayerStatus;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.advanceprocessors.advances.Advance;
import com.services.fieldinitializers.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PiecesFieldModel implements Serializable {
    public static PiecesFieldModel instance;
    private final FieldManager fieldManager;
    private final PlayerStatus playerStatus;
    private transient Point prevCoordinates;
    private boolean hasMovedAtLeastOnce;
    private boolean isDisabled;

    private PiecesFieldModel() {
        List<FieldInitializer> fieldInitializers = new ArrayList<>();
        fieldInitializers.add(new CheckFieldInitializer());
        fieldManager = FieldManager.with(fieldInitializers);
        playerStatus = PlayerStatus.fromInitialPlayer(PlayerType.FIRST);
    }

    public static void resetModel() {
        PiecesFieldModel.instance = new PiecesFieldModel();
    }

    public static PiecesFieldModel getInstance() {
        if (PiecesFieldModel.instance == null) {
            PiecesFieldModel.instance = new PiecesFieldModel();
        }

        return PiecesFieldModel.instance;
    }

    public static void setInstance(PiecesFieldModel instance) {
        PiecesFieldModel.instance = instance;
    }

    public boolean hasActivatedThePieceBefore() {
        return prevCoordinates != null;
    }

    public boolean isRegisteringAnEmptyCellAt(Point coordinates) {
        return !hasActivatedThePieceBefore() && isCellEmptyAt(coordinates);
    }

    public void savePrevCoordinates(Point coordinates) {
        prevCoordinates = coordinates;
    }

    public void clearPrevCoordinates() {
        prevCoordinates = null;
    }

    public boolean hasPieceSelected() {
        return hasActivatedThePieceBefore();
    }

    public boolean isCellEmptyAt(Point coordinates) {
        return fieldManager.getField().get(coordinates).getPieceType() == PieceType.EMPTY;
    }

    public boolean isOwnPieceAt(Point coordinates) {
        PlayerType piecePlayerType = fieldManager.getField().get(coordinates).getPlayerType();

        if (piecePlayerType.equals(PlayerType.NONE)) {
            return true;
        }

        return fieldManager.getField().get(coordinates).getPlayerType()
                .equals(playerStatus.getCurrentPlayer());
    }

    public void movePieceTo(Advance advance) throws IllegalPieceMoveException {
        getFieldManager().move(getPrevCoordinates(), advance);
    }

    public boolean isReadyToCaptureAt(Point coordinates) {
        return !isOwnPieceAt(coordinates) && hasPieceSelected();
    }

    public boolean isUnderCheck(PlayerType currentPlayerType) {
        PlayerType playerUnderCheck = PiecesFieldModel.getInstance().getPlayerStatus().getPlayerUnderCheck();
        return playerUnderCheck == currentPlayerType;
    }

    public boolean isUnderCheckCurrentPlayer() {
        return isUnderCheck(getPlayerStatus().getCurrentPlayer());
    }

    public boolean hasSelectedTheSamePieceAt(Point coordinates) {
        return prevCoordinates.equals(coordinates);
    }

    public Advance getAdvanceByDestination(Point destination) throws NoSuchElementException {
        return fieldManager.getField().get(prevCoordinates).getPossibleAdvances().getAdvanceByMove(destination);
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

    public void setHasMovedAtLeastOnce(boolean hasMovedAtLeastOnce) {
        this.hasMovedAtLeastOnce = hasMovedAtLeastOnce;
    }
}
