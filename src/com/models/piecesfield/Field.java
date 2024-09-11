package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.*;
import com.models.pieces.abstractpiece.Piece;
import com.services.fieldinitializers.FieldInitializer;
import com.services.advanceprocessors.Advance;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Field implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Piece> field;

    void initializeWith(FieldInitializer initializer) {
        field = initializer.initialize();
    }

    public Field(List<Piece> field) {
        this.field = field;
    }

    public Piece get(Point point) {
        int index = IndexCalculatorByPoint.getIndex(point);
        return field.get(index);
    }

    public void move(Point src, Advance advance) throws IllegalPieceMoveException {
        if (src.equals(advance.getDestination())) {
            return;
        }

        Point possibleCapture = advance.getPossibleCapture();
        if (possibleCapture != null) {
            captureAt(possibleCapture);
        }

        get(src).moveTo(advance.getDestination());
        swap(src, advance.getDestination());
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
