package com.controller;

import com.models.piecesfield.PiecesFieldModel;
import com.services.playerpiecefactories.PieceFactoriesByPlayer;

public class SwapPlayersController {
    public static void swapPlayers() throws IllegalStateException {
        if (PiecesFieldModel.getInstance().hasMovedAtLeastOnce()) {
            throw new IllegalStateException();
        }
        PiecesFieldModel.getInstance().getPlayerStatus().switchPlayer();
        PieceFactoriesByPlayer.swapColors();
        PiecesFieldController.getInstance().updatePiecesPanel();
    }
}
