package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.Piece;
import com.models.piecesfield.fieldinitializers.FieldInitializer;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Field {
    private final List<Piece> field;

    void initializeWith(FieldInitializer initializer) {
        initializer.initialize(this);
    }

    Field(List<Piece> field) {
        this.field = field;
    }

    Piece get(Point point) {
        int index = IndexCalculatorByPoint.getIndex(point);
        return field.get(index);
    }

    public void swap(Point src, Point dest) {
        int srcIndex = IndexCalculatorByPoint.getIndex(src);
        int destIndex = IndexCalculatorByPoint.getIndex(src);

        Collections.swap(field, srcIndex, destIndex);
//        field.getField().set(srcIndex, )
    }

    List<Piece> getList() {
        return field;
    }
}
