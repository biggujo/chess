package com.models.piecesfield;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.abstractpiece.Piece;
import com.services.fieldinitializers.FieldInitializer;
import com.services.advanceprocessors.advances.Advance;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FieldManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Field field;
    private transient List<FieldInitializer> initializers;
    private transient ComponentsManager componentsManager;

    public static FieldManager with(List<FieldInitializer> initializers) {
        return new FieldManager(initializers);
    }

    private FieldManager(List<FieldInitializer> initializers) {
        this.initializers = initializers;
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

    public void setField(Field field) {
        this.field = field;
        initializeComponents();
    }

    public Components getComponents() {
        return componentsManager.getComponents();
    }

    public void addInitializer(FieldInitializer initializer) {
        this.initializers.add(initializer);
    }

    private void initializeField() {
        field = new Field(new ArrayList<>());

        initializers.forEach(initializer -> field.initializeWith(initializer));
    }

    public void initializeComponents() {
        componentsManager = ComponentsManager.from(field);
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(field);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        field = (Field) stream.readObject();
        initializers = new ArrayList<>();
        initializeComponents();
    }
}
