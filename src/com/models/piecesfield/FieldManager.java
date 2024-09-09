package com.models.piecesfield;

import com.models.pieces.IllegalPieceMoveException;
import com.models.piecesfield.fieldinitializers.EmptyFieldInitializer;
import com.models.piecesfield.fieldinitializers.FieldInitializer;
import com.services.moves.Advance;

import java.awt.*;
import java.util.ArrayList;

class FieldManager {
    private Field field;
    private ComponentsManager componentsManager;

    public FieldManager() {
        initializeField();
        initializeComponents();
    }

    public void move(Point src, Advance advance) throws IllegalPieceMoveException {
        field.move(src, advance);
        componentsManager.updateWith(field);
    }

    public Field getField() {
        return field;
    }

    public Components getComponents() {
        return componentsManager.getComponents();
    }

    private void initializeField() {
        field = new Field(new ArrayList<>());
        FieldInitializer initializer = new EmptyFieldInitializer();
        field.initializeWith(initializer);
    }

    private void initializeComponents() {
        componentsManager = ComponentsManager.from(field);
    }
}
