package com.models.piecesfield;

import com.helpers.IndexCalculatorByPoint;
import com.models.pieces.*;
import com.models.pieces.abstractpiece.Piece;
import com.services.fieldinitializers.FieldInitializer;
import com.services.advanceprocessors.advances.Advance;
import org.apache.commons.lang3.SerializationUtils;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Piece> pieces;

    void initializeWith(FieldInitializer initializer) {
        pieces = initializer.initialize();
    }

    public Field() {
        this.pieces = new ArrayList<>();
    }

    public Field(Field field) {
        this();

        field.getList().forEach(p -> pieces.add(SerializationUtils.clone(p)));
    }

    public Piece get(Point point) throws NullPointerException {
        if (point == null) {
            throw new NullPointerException();
        }

        int index = IndexCalculatorByPoint.getIndex(point);
        return pieces.get(index);
    }

    public void set(Piece piece) {
        int index = IndexCalculatorByPoint.getIndex(piece.getStatus().getCoordinates());
        pieces.set(index, piece);
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

        Collections.swap(pieces, srcIndex, destIndex);

        Piece movedPiece = get(src);

        if (isMovedPieceEmpty(movedPiece)) {
            setEmptyPieceAt(src);
        }
    }

    public void captureAt(Point coordinates) {
        setEmptyPieceAt(coordinates);
    }

    public List<Piece> getList() {
        return pieces;
    }

    public List<Piece> getOpponentPiecesBy(PlayerType playerType) {
        if (playerType == PlayerType.FIRST) {
            playerType = PlayerType.SECOND;
        } else {
            playerType = PlayerType.FIRST;
        }

        return getPiecesBy(playerType);
    }

    public List<Piece> getPiecesBy(PlayerType playerType) {
        return pieces.stream()
                .filter(p -> p.getPlayerType() == playerType)
                .toList();
    }

    public boolean isEmptyAt(Point coordinates) throws IndexOutOfBoundsException {
        int index = IndexCalculatorByPoint.getIndex(coordinates);

        return pieces.get(index).getPieceType() == PieceType.EMPTY;
    }

    private boolean isMovedPieceEmpty(Piece piece) {
        return piece.getPieceType() != PieceType.EMPTY;
    }

    private void setEmptyPieceAt(Point coordinates) {
        int index = IndexCalculatorByPoint.getIndex(coordinates);
        pieces.set(index, new PieceFactory(PlayerType.FIRST).getInstance(PieceType.EMPTY, coordinates));
    }
}
