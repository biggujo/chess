package com.controller;

import com.models.piecesfield.PiecesFieldModel;

import java.lang.reflect.InvocationTargetException;

public class GameManager {
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
