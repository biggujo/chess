package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.EmptyPiece;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.Piece;
import com.models.pieces.PieceType;
import com.models.piecesfield.fieldinitializers.FieldInitializer;

import java.awt.*;
import java.util.ArrayList;
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

    public Piece get(Point point) {
        int index = IndexCalculatorByPoint.getIndex(point);
        return field.get(index);
    }

    public void move(Point src, Point dest) throws IllegalPieceMoveException {
        get(src).moveTo(dest);
        swap(src, dest);
    }

    public void swap(Point src, Point dest) {
        int srcIndex = IndexCalculatorByPoint.getIndex(src);
        int destIndex = IndexCalculatorByPoint.getIndex(dest);

        Collections.swap(field, srcIndex, destIndex);

        Piece movedPiece = get(src);

        if (isMovedPieceEmpty(movedPiece)) {
            setEmptyPieceAt(src);
        }
    }

    public List<Piece> getList() {
        return field;
    }

    private boolean isMovedPieceEmpty(Piece piece) {
        return piece.getType() != PieceType.EMPTY;
    }

    private void setEmptyPieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        field.set(index, new EmptyPiece(coordinates, new ArrayList<>()));
    }
}
