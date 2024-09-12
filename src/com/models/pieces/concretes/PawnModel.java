package com.models.pieces.concretes;

import com.globals.Defaults;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.PieceImpl;
import com.services.advanceprocessors.processorlists.AdvanceProcessorsStorage;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class PawnModel extends PieceImpl {
    private static final PieceType type = PieceType.PAWN;

    public PawnModel(PlayerType playerType, Point coordinates) {
        super(playerType, coordinates, AdvanceProcessorsStorage.forPawn());
    }

    @Override
    public void revalidatePossibleAdvances() {
        if (hasReachedTheTop()) {
            return;
        }

        super.revalidatePossibleAdvances();
    }

    private boolean hasReachedTheTop() {
        if (isFirstPlayer()) {
            return getStatus().getCoordinates().y == 0;
        }

        return getStatus().getCoordinates().y == Defaults.TILE_AMOUNT - 1;
    }

    @Override
    public PieceType getPieceType() {
        return PawnModel.type;
    }

    private boolean isFirstPlayer() {
        return getPlayerType() == PlayerType.FIRST;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        writeObjectCommon(stream);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        readObjectCommon(stream);
        setAdvanceProcessors(AdvanceProcessorsStorage.forPawn());
    }
}
