package com.services.fieldlisteners;

import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.services.advanceprocessors.advances.Advance;
import com.services.advanceprocessors.advances.Advances;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class CheckOnMoveEndListener implements OnMoveEndListener {
    private static final Supplier<Field> FIELD_SUPPLIER = () -> PiecesFieldModel.getInstance().getField();

    @Override
    public void accept(Piece currentPiece, Piece capturedPiece) {
        currentPiece.revalidatePossibleAdvancesWith(FIELD_SUPPLIER.get());
        Advances advances = currentPiece.getPossibleAdvances();

        Advance checkAdvance = advances.getAvailableAdvances()
                .stream()
                .filter(a -> {
                    Point possibleCaptureCoordinates = a.getPossibleCapture();

                    if (possibleCaptureCoordinates == null) {
                        return false;
                    }

                    Piece possibleCapturePiece = getPieceAt(possibleCaptureCoordinates);

                    if (possibleCapturePiece == null) {
                        return false;
                    }

                    return possibleCapturePiece.getPieceType() == PieceType.KING;
                })
                .findFirst()
                .orElse(null);

        if (checkAdvance == null) {
            return;
        }

        setCheckFor(checkAdvance);
        JOptionPane.showMessageDialog(null, "Check!");
    }

    private Piece getPieceAt(Point coordinates) {
        return PiecesFieldModel.getInstance().getField().get(coordinates);
    }

    private void setCheckFor(Advance advance) {
        Piece kingOfTheOtherPlayer = getPieceAt(advance.getPossibleCapture());
        PlayerType otherPlayerType = kingOfTheOtherPlayer.getPlayerType();
        PiecesFieldModel.getInstance().getPlayerStatus().setCheckFor(otherPlayerType);
    }
}
