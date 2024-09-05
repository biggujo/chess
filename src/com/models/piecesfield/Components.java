package com.models.piecesfield;

import com.view.pieces.PieceFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Components {
    private final ArrayList<JComponent> components;

    public static Components fromField(Field field) {
        Field field1 = new Field(field.getList());
        field1.getList().sort(Comparable::compareTo);

        return new Components(field1);
    }

    private Components(Field field) {
        components = new ArrayList<>();

        field.getList().forEach(pieceModel -> {
            JComponent pieceInstance = PieceFactory.getInstance(pieceModel.getType());
            components.add(pieceInstance);
        });
    }

    public JComponent get(int index) {
        return components.get(index);
    }

    public boolean add(JComponent jComponent) {
        return components.add(jComponent);
    }

    public void swap(int srcIndex, int destIndex) {
        Collections.swap(components, srcIndex, destIndex);
    }

    public List<JComponent> getComponents() {
        return components;
    }
}
