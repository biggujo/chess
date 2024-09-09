package com.services.moves.pawn;

import com.helpers.PointTranslator;
import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.abstractpiece.Piece;
import com.models.pieces.PlayerType;
import com.models.piecesfield.PiecesFieldModel;
import com.services.moves.Advance;
import com.services.moves.AdvanceProcessorImpl;

import java.awt.*;

abstract class ForwardAdvanceProcessor extends AdvanceProcessorImpl {
    public ForwardAdvanceProcessor(Piece piece) {
        super(piece);
    }

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

    protected boolean isFirstPlayerPiece() {
        return getPiece().getPlayerType() == PlayerType.FIRST;
    }

    private boolean isBusyCellAt(Point coordinates) throws IndexOutOfBoundsException {
        return !PiecesFieldModel.getField().isEmptyAt(coordinates);
    }
}
