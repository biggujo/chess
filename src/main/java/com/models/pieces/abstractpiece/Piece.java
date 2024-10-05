package com.models.pieces.abstractpiece;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.piecesfield.Field;
import com.services.advanceprocessors.advances.Advances;

import java.awt.*;
import java.io.Serializable;

public interface Piece extends Comparable<Piece>, Serializable {
    void revalidatePossibleAdvancesWith(Field field);

    PieceType getPieceType();

    PlayerType getPlayerType();

    Status getStatus();

    Advances getPossibleAdvances();

    void setPossibleAdvances(Advances advances);

    boolean hasReachedTheTop();

    int getValue();

    void moveTo(Point point) throws IllegalPieceMoveException;
}
