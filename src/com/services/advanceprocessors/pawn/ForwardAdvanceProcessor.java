package com.services.advanceprocessors.pawn;

import com.helpers.PointTranslator;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.PiecesFieldModel;
import com.services.advanceprocessors.Advance;
import com.services.advanceprocessors.AdvanceProcessorImpl;

import java.awt.*;

abstract class ForwardAdvanceProcessor extends AdvanceProcessorImpl {
    protected void addForwardBy(int dy) throws IllegalPieceMoveException {
        if (isFirstPlayerPiece()) {
            dy = -dy;
        }

        Point srcCoordinates = getPiece().getStatus().getCoordinates();
        Point destCoordinates = PointTranslator.translate(srcCoordinates, 0, dy);

        try {
            if (isBusyCellAt(destCoordinates)) {
                return;
            }
        } catch (IndexOutOfBoundsException ignored) {
            return;
        }

        Advance advance = new Advance(destCoordinates, null);
        add(advance);
    }

    private boolean isBusyCellAt(Point coordinates) throws IndexOutOfBoundsException {
        return !PiecesFieldModel.getInstance().getField().isEmptyAt(coordinates);
    }
}
