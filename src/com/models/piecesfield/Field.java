package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.*;
import com.models.piecesfield.fieldinitializers.FieldInitializer;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Field {
    private List<Piece> field;

    void initializeWith(FieldInitializer initializer) {
        field = initializer.initialize(this.getList());
    }

    public Field(List<Piece> field) {
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

    public void captureAt(Point coordinates) {
        setEmptyPieceAt(coordinates);
    }

    public List<Piece> getList() {
        return field;
    }

    public boolean isEmptyAt(Point coordinates) throws IndexOutOfBoundsException {
        int index = IndexCalculatorByPoint.getIndex(coordinates);

        return field.get(index).getPieceType() == PieceType.EMPTY;
    }

    private boolean isMovedPieceEmpty(Piece piece) {
        return piece.getPieceType() != PieceType.EMPTY;
    }

    private void setEmptyPieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        field.set(index, new PieceFactory(PlayerType.FIRST).getInstance(PieceType.EMPTY, coordinates));
    }
}
