package com.controller;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.*;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.services.advanceprocessors.advances.Advance;
import com.view.ViewManager;
import com.view.pieces.PieceComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

public class PiecesFieldController {
    private static PiecesFieldController instance;

    public static PiecesFieldController getInstance() {
        if (PiecesFieldController.instance == null) {
            PiecesFieldController.instance = new PiecesFieldController();
        }

        return PiecesFieldController.instance;
    }

    private final PiecesFieldModel model = PiecesFieldModel.getInstance();

    public Piece captureAt(Point coordinates) throws NoMoveHasBeenMadeException {
        if (model.isUnderCheckCurrentPlayer() && !isKingSelected(coordinates)) {
            throw new NoMoveHasBeenMadeException();
        }

        if (model.isDisabled()) {
            throw new NoMoveHasBeenMadeException();
        }

        if (model.isRegisteringAnEmptyCellAt(coordinates)) {
            throw new NoMoveHasBeenMadeException();
        }

        if (model.isReadyToCaptureAt(coordinates)) {
            return tryToMoveTo(coordinates);
        }

        if (!model.isOwnPieceAt(coordinates)) {
            throw new NoMoveHasBeenMadeException();
        }

        if (!model.hasPieceSelected()) {
            selectPieceAt(coordinates);
            AvailableMovesController.updateAvailableMovesPanel(coordinates);
            throw new NoMoveHasBeenMadeException();
        }

        if (model.hasSelectedTheSamePieceAt(coordinates)) {
            AvailableMovesController.clearAvailableMovesPanel();
            unselectPiece();
            throw new NoMoveHasBeenMadeException();
        }

        if (!model.getField().isEmptyAt(coordinates)) {
            selectAnotherPieceAt(coordinates);
            throw new NoMoveHasBeenMadeException();
        }

        return tryToMoveTo(coordinates);
    }

    public void disablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) model.getFieldManager().getComponents().get(index);
        pieceComponent.setInactive();
    }

    public void enablePieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        PieceComponent pieceComponent = (PieceComponent) model.getFieldManager().getComponents().get(index);
        pieceComponent.setActive();
    }

    public void replacePieceAt(Point coordinates, PlayerType playerType, PieceType pieceType) {
        Field field = PiecesFieldModel.getInstance().getFieldManager().getField();

        PieceFactory factory = new PieceFactory(playerType);
        Piece newPiece = factory.getInstance(pieceType, coordinates);
        field.set(newPiece);

        updatePiecesPanel();
    }

    public void updatePiecesPanel() {
        PiecesFieldModel.getInstance().getFieldManager().initializeComponents();
        List<JComponent> updatedField = PiecesFieldModel.getInstance().getComponents();

        SwingUtilities.invokeLater(() -> {
            ViewManager.getInstance().getPiecesPanel().updateWith(updatedField);
            ViewManager.getInstance().getPiecesPanel().revalidate();
        });
    }

    public void enableField() {
        PiecesFieldModel.getInstance().setDisabled(false);
    }

    public void disableField() {
        PiecesFieldModel.getInstance().setDisabled(true);
    }

    private void selectPieceAt(Point coordinates) {
        enablePieceAt(coordinates);
        model.getFieldManager().getField().get(coordinates).revalidatePossibleAdvancesWith(model.getField());
        model.savePrevCoordinates(coordinates);
    }

    private void unselectPiece() {
        disablePieceAt(model.getPrevCoordinates());
        model.clearPrevCoordinates();
    }

    private void selectAnotherPieceAt(Point coordinates) {
        disablePieceAt(model.getPrevCoordinates());
        enablePieceAt(coordinates);
        AvailableMovesController.updateAvailableMovesPanel(coordinates);

        model.savePrevCoordinates(coordinates);
    }

    private Piece tryToMoveTo(Point point) {
        Piece capturedPiece = null;
        try {
            capturedPiece = model.getFieldManager().getField().get(point);

            Advance advance = model.getAdvanceByDestination(point);
            model.movePieceTo(advance);
            model.setHasMovedAtLeastOnce(true);
            model.getPlayerStatus().switchPlayer();

            return capturedPiece;
        } catch (IllegalPieceMoveException | NoSuchElementException ignored) {
        } finally {
            AvailableMovesController.clearAvailableMovesPanel();
            unselectPiece();
        }

        return capturedPiece;
    }

    private boolean isKingSelected(Point coordinates) {
        boolean areCurrentCoordinatesContainKing = model.getField().get(coordinates).getPieceType() == PieceType.KING;

        if (model.getPrevCoordinates() == null) {
            return areCurrentCoordinatesContainKing;
        }

        boolean arePrevCoordinatesContainKing = model.getField().get(model.getPrevCoordinates()).getPieceType() == PieceType.KING;

        return arePrevCoordinatesContainKing || areCurrentCoordinatesContainKing;
    }
}
