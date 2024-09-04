package com.controller;

import com.globals.Defaults;
import com.model.PiecesFieldModel;
import com.view.panels.PiecesPanel;
import com.view.pieces.Piece;
import com.view.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class FieldController {
    private static FieldController instance;
    private final PiecesFieldModel piecesFieldModel = PiecesFieldModel.getInstance();
    private int prevActivePieceIndex = -1;
    private Point prevCoordinates;

    public static FieldController getInstance() throws IOException {
        if (FieldController.instance == null) {
            FieldController.instance = new FieldController();
        }

        return instance;
    }

    private FieldController() throws IOException {
    }

    public void toggleActivePiece(Point coordinates) throws IOException {
        if (coordinates == null) {
            throw new NullPointerException();
        }

        int activePieceIndex = Defaults.TILE_AMOUNT * coordinates.y + coordinates.x;

        if (prevCoordinates != null && !coordinates.equals(prevCoordinates)) {
            PiecesFieldModel.getInstance().movePiece(prevCoordinates, coordinates);
            PiecesPanel.getInstance().updateCells(getField());
            disablePieceAt(activePieceIndex);
            prevCoordinates = null;
            return;
        }

        if (activePieceIndex == prevActivePieceIndex) {
            disablePieceAt(activePieceIndex);
            forgetPrevPointIndex();
            return;
        }

        if (prevCoordinates == null && piecesFieldModel.getPieceTypeAt(coordinates) == PieceType.EMPTY) {
            return;
        }

        enablePieceAt(activePieceIndex);

        boolean hasChosenBefore = prevActivePieceIndex != -1;

        if (hasChosenBefore) {
            disablePieceAt(prevActivePieceIndex);
        }

        saveCurrentActiveIndex(activePieceIndex);
        saveCurrentCoordinates(coordinates);
    }

    public List<JComponent> getField() {
        return piecesFieldModel.getFieldComponents();
    }

    private void disablePieceAt(int index) {
        Piece piece = (Piece) getField().get(index);
        piece.setInactive();
    }

    private void enablePieceAt(int index) {
        Piece piece = (Piece) getField().get(index);
        piece.setActive();
    }

    private void forgetPrevPointIndex() {
        prevActivePieceIndex = -1;
    }

    private void saveCurrentActiveIndex(int index) {
        prevActivePieceIndex = index;
    }

    private void saveCurrentCoordinates(Point coordinates) {
        prevCoordinates = coordinates;
    }
}
