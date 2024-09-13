package com.models.pieces;

import com.models.pieces.abstractpiece.Piece;
import com.models.pieces.concretes.*;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PieceFactory {
    private final PlayerType playerType;

    public PieceFactory(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Piece getInstance(PieceType pieceType, Point coordinates) {
        if (pieceType == PieceType.EMPTY) {
            return new EmptyPiece(coordinates);
        }

        try {
            Class<? extends Piece> pieceClass = getModelClassBy(pieceType);
            return newInstanceByClass(pieceClass, coordinates);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<? extends Piece> getModelClassBy(PieceType pieceType) {
        return switch (pieceType) {
            case PAWN -> PawnModel.class;
            case ROOK -> RookModel.class;
            case BISHOP -> BishopModel.class;
            case KNIGHT -> KnightModel.class;
            case KING -> KingModel.class;
            case QUEEN -> QueenModel.class;
            default -> throw new IllegalStateException("Unexpected value: " + pieceType);
        };
    }

    private Piece newInstanceByClass(Class<? extends Piece> pieceClass, Point coordinates) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<? extends Piece> constructor = pieceClass.getConstructor(PlayerType.class, Point.class);
        return constructor.newInstance(playerType, coordinates);
    }
}
