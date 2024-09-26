package com.controller;

import com.models.pieces.NoMoveHasBeenMadeException;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.services.fieldlisteners.OnMoveEndListener;
import com.services.fieldlisteners.PawnPromotionOnMoveEndListener;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final List<OnMoveEndListener> onMoveEndListeners;

    static {
        onMoveEndListeners = new ArrayList<>();
        onMoveEndListeners.add(new PawnPromotionOnMoveEndListener());
    }

    public static void handleClickAt(Point coordinates) throws IOException {
        try {
            Piece currentPiece = PiecesFieldModel.getInstance().getCurrentPiece();

            PiecesFieldController controller = PiecesFieldController.getInstance();

            Piece capturedPiece = controller.captureAt(coordinates);
            controller.updatePiecesPanel();
            CapturedPiecesController.add(capturedPiece);

            runAllOnMoveEndListeners(currentPiece, capturedPiece);
        } catch (NoMoveHasBeenMadeException ignored) {
        }
    }

    public static void resetGame() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        resetGame(null);
    }

    public static void resetGame(PiecesFieldModel piecesFieldModel) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if (piecesFieldModel == null) {
            PiecesFieldModel.resetModel();
            piecesFieldModel = PiecesFieldModel.getInstance();
        }

        PiecesFieldModel.setInstance(piecesFieldModel);
        PiecesFieldController.getInstance().updatePiecesPanel();
        AvailableMovesController.clearAvailableMovesPanel();
    }

    private static void runAllOnMoveEndListeners(Piece currentPiece, Piece capturedPiece) {
        onMoveEndListeners.forEach(l -> l.accept(currentPiece, capturedPiece));
    }
}
