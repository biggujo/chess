package com.controller;

import com.models.captures.PlayerCaptures;
import com.models.captures.CapturesModel;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.view.ViewManager;
import com.view.panels.captures.CapturesPanel;

public class CapturedPiecesController {
    private static final PlayerCaptures PLAYER_CAPTURES = new PlayerCaptures();

    public static void add(Piece piece) {
        CapturesPanel selectedCapturedPanel = retrievePanelBy(piece.getPlayerType());

        boolean isCapturedEmptyPiece = selectedCapturedPanel == null;
        if (isCapturedEmptyPiece) {
            return;
        }

        CapturesModel currentCapturesModel = PLAYER_CAPTURES.get(piece.getPlayerType());
        saveCapture(currentCapturesModel, piece);
        updateCapturesViewPieces(currentCapturesModel, selectedCapturedPanel);
        updateCapturesViewScore(currentCapturesModel, selectedCapturedPanel);
    }

    private static CapturesPanel retrievePanelBy(PlayerType playerType) {
        return ViewManager.getInstance().getCapturesPanelBy(playerType);
    }

    private static CapturesPanel retrieveOpponentPanelBy(PlayerType playerType) {
        return ViewManager.getInstance().getOpponentCapturesPanelBy(playerType);
    }

    private static void saveCapture(CapturesModel model, Piece piece) {
        model.add(piece);
    }

    private static void updateCapturesViewPieces(CapturesModel model, CapturesPanel panel) {
        panel.updateCapturesWith(model.getComponents());
    }

    private static void updateCapturesViewScore(CapturesModel model, CapturesPanel panel) {
        panel.setScore(model.getTotalValue());
    }
}
