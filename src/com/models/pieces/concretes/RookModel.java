package com.models.pieces.concretes;

import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.PieceImpl;
import com.services.advanceprocessors.processorlists.AdvanceProcessorsStorage;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class RookModel extends PieceImpl {
    public RookModel(PlayerType playerType, Point coordinates) {
        super(playerType, coordinates, AdvanceProcessorsStorage.forRook());
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        writeObjectCommon(stream);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        readObjectCommon(stream);
        setAdvanceProcessors(AdvanceProcessorsStorage.forRook());
    }
}
