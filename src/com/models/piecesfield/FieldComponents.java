package com.models.piecesfield;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FieldComponents {
    private final List<JComponent> fieldComponents;

    public FieldComponents() {
        this.fieldComponents = new ArrayList<>();
    }

    public JComponent get(int index) {
        return fieldComponents.get(index);
    }

    public boolean add(JComponent jComponent) {
        return fieldComponents.add(jComponent);
    }

    public void swap(int srcIndex, int destIndex) {
        Collections.swap(fieldComponents, srcIndex, destIndex);
    }

    public List<JComponent> getFieldComponents() {
        return fieldComponents;
    }
}
