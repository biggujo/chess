package com.models.pieces.concretes;

import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.PieceImpl;
import com.models.pieces.abstractpiece.Status;
import com.services.advanceprocessors.AdvanceProcessors;
import com.services.advanceprocessors.Advances;
import com.services.advanceprocessors.empty.EmptyAdvanceProcessors;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class EmptyPiece extends PieceImpl {
    private static final AdvanceProcessors ADVANCE_PROCESSORS = new EmptyAdvanceProcessors();
    private static final PieceType type = PieceType.EMPTY;

    public EmptyPiece(Point coordinates) {
        super(PlayerType.NONE, coordinates, ADVANCE_PROCESSORS);
    }

    @Override
    protected void addPossibleAdvances() {
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.NONE;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        writeObjectCommon(stream);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        readObjectCommon(stream);
        setAdvanceProcessors(EmptyPiece.ADVANCE_PROCESSORS);
    }
}
