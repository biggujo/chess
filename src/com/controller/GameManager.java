package com.controller;

import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GameManager {
    public static void handleClickAt(Point coordinates) throws IOException {
        Piece piece = PiecesFieldModel.getInstance().captureAt(coordinates);

        if (piece == null) {
            return;
        }

        PiecesFieldController.updatePiecesPanel();
        CapturedPiecesController.add(piece);
    }

    public static void resetGame() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        resetGame(null);
    }

    public static void resetGame(PiecesFieldModel piecesFieldModel) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if (piecesFieldModel == null) {
            piecesFieldModel = new PiecesFieldModel();
        }

        PiecesFieldModel.setInstance(piecesFieldModel);
        PiecesFieldController.updatePiecesPanel();
        AvailableMovesController.clearAvailableMovesPanel();
    }
}
