package com.services.fieldlisteners;

import com.controller.PiecesFieldController;
import com.models.pieces.PieceType;
import com.models.pieces.abstractpiece.Piece;
import com.view.frames.promotion.PromotionFrame;

import javax.swing.*;

public class PawnPromotionFieldListener implements FieldListener {
    @Override
    public void onMoveEnds(Piece originalPiece, Piece capturedPiece) {
        if (originalPiece == null) {
            throw new NullPointerException();
        }

        boolean isAtTheTop = originalPiece.hasReachedTheTop();
        boolean isPawn = originalPiece.getPieceType() == PieceType.PAWN;

        if (!isAtTheTop || !isPawn) {
            return;
        }

        PiecesFieldController.disableField();
        JDialog promotionFrame = new PromotionFrame(originalPiece.getPlayerType(), originalPiece.getStatus().getCoordinates());
        promotionFrame.setVisible(true);
    }
}
