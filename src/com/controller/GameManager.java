package com.controller;

import com.models.pieces.NoMoveHasBeenMadeException;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.services.fieldlisteners.FieldListeners;
import com.services.fieldlisteners.PawnPromotionFieldListener;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GameManager {
    private static final FieldListeners fieldListeners;

    static {
        fieldListeners = new FieldListeners();
        fieldListeners.add(new PawnPromotionFieldListener());
    }

    public static void handleClickAt(Point coordinates) throws IOException {
        try {
            Piece currentPiece = PiecesFieldModel.getInstance().getCurrentPiece();
            Piece capturedPiece = PiecesFieldModel.getInstance().captureAt(coordinates);
            PiecesFieldController.updatePiecesPanel();
            CapturedPiecesController.add(capturedPiece);

            fieldListeners.runAll(currentPiece, capturedPiece);
        } catch (NoMoveHasBeenMadeException ignored) {
        }
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
