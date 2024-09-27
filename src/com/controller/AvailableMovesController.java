package com.controller;

import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.view.ViewManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AvailableMovesController {
    public static void updateAvailableMovesPanel(Point coordinates) {
        Piece piece = PiecesFieldModel.getInstance().getField().get(coordinates);
        List<Point> availableMoves = piece.getPossibleAdvances().getAvailableMoves();
        ViewManager.getInstance().getAvailableMovesPanel().setAvailableMoves(availableMoves);
    }

    public static void clearAvailableMovesPanel() {
        ViewManager.getInstance().getAvailableMovesPanel().setAvailableMoves(new ArrayList<>());
    }
}
