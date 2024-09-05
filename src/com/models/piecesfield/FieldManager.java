package com.models.piecesfield;

import com.models.piecesfield.fieldinitializers.EmptyFieldInitializer;
import com.models.piecesfield.fieldinitializers.FieldInitializer;

import java.util.ArrayList;

class FieldManager {
    private final Field field;

    public FieldManager() {
        field = new Field(new ArrayList<>());
        FieldInitializer initializer = new EmptyFieldInitializer();
        field.initializeWith(initializer);
    }

    public Field getField() {
        return field;
    }
}
