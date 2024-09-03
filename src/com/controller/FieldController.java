package com.controller;

import com.model.FieldModel;
import com.model.PiecesFieldModel;

import javax.swing.*;
import java.util.List;

public class FieldController {
    private static FieldController instance;
    private final PiecesFieldModel piecesFieldModel = new PiecesFieldModel(FieldModel.FIELD);

    public static FieldController getInstance() {
        if (FieldController.instance == null) {
            FieldController.instance = new FieldController();
        }

        return instance;
    }

    private FieldController() {
    }

    public List<JComponent> getField() {
        return piecesFieldModel.getField();
    }
}
