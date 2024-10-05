package com.services.fieldlisteners;

import com.controller.PiecesFieldController;
import com.models.pieces.PieceType;
import com.models.pieces.abstractpiece.Piece;
import com.view.frames.promotion.PromotionFrame;

import javax.swing.*;

public class PawnPromotionOnMoveEndListener implements OnMoveEndListener {
    @Override
    public void accept(Piece originalPiece, Piece capturedPiece) {
        if (originalPiece == null) {
            throw new NullPointerException();
        }

        boolean isAtTheTop = originalPiece.hasReachedTheTop();
        boolean isPawn = originalPiece.getPieceType() == PieceType.PAWN;

        if (!isAtTheTop || !isPawn) {
            return;
        }

        PiecesFieldController.getInstance().disableField();
        JDialog promotionFrame = new PromotionFrame(originalPiece.getPlayerType(), originalPiece.getStatus().getCoordinates());
        promotionFrame.setVisible(true);
    }
}
