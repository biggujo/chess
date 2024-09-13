package com.models.pieces.concretes;

import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.PieceImpl;
import com.services.advanceprocessors.processorlists.AdvanceProcessors;
import com.services.advanceprocessors.processorlists.AdvanceProcessorsStorage;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.function.Supplier;

public class KnightModel extends PieceImpl {
    private static final Supplier<AdvanceProcessors> ADVANCE_PROCESSORS_SUPPLIER = AdvanceProcessorsStorage::forKnight;

    public KnightModel(PlayerType playerType, Point coordinates) {
        super(playerType, coordinates, ADVANCE_PROCESSORS_SUPPLIER.get());
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        writeObjectCommon(stream);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        readObjectCommon(stream);
        setAdvanceProcessors(ADVANCE_PROCESSORS_SUPPLIER.get());
    }
}
