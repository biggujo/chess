package com.controller;

import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.view.panels.PanelManager;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AvailableMovesController {
    public static void updateAvailableMovesPanel(Point coordinates) throws IOException {
        Piece piece = PiecesFieldModel.getInstance().getField().get(coordinates);
        List<Point> availableMoves = piece.getAdvancesList().getAvailableMoves();
        PanelManager.getInstance().getAvailableMovesPanel().setAvailableMoves(availableMoves);
    }

    public static void clearAvailableMovesPanel() throws IOException {
        PanelManager.getInstance().getAvailableMovesPanel().setAvailableMoves(new ArrayList<>());
    }
}
