package com.services.advanceprocessors.processors;

import com.models.pieces.IllegalPieceMoveException;
import com.models.pieces.PlayerType;
import com.models.pieces.abstractpiece.Piece;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.services.advanceprocessors.advances.Advance;
import com.services.advanceprocessors.advances.Advances;
import org.apache.commons.lang3.SerializationUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KingOrthogonalAdvanceProcessor extends OrthogonalAdvanceProcessor {
    public KingOrthogonalAdvanceProcessor(int maxDiff) {
        super(maxDiff);
    }

    @Override
    protected List<Advance> modifyPossibleAdvances(List<Advance> originalAdvances) {
        if (!isUnderCheck()) {
            return originalAdvances;
        }

        List<Advance> possibleKingAdvances = new ArrayList<>(originalAdvances);

        List<Piece> opponentPieces = getOpponentPieces();

        for (int i = 0; i < possibleKingAdvances.size(); i++) {
            Advance kingPossibleAdvance = possibleKingAdvances.get(i);

            for (Piece opponentPiece : opponentPieces) {
                Field copyField;
                try {
                    copyField = createMockFieldWithKingMoveTo(
                            getPiece().getStatus().getCoordinates(),
                            kingPossibleAdvance,
                            new Advances(possibleKingAdvances));
                } catch (IllegalPieceMoveException ignored) {
                    break;
                }

                opponentPiece.revalidatePossibleAdvancesWith(copyField);
                List<Point> availableCaptures = opponentPiece.getPossibleAdvances().getAvailableCaptures();

                boolean hasRemovedAtLeastOnce = false;

                for (Point availableCapture : availableCaptures) {
                    if (kingPossibleAdvance.getDestination().equals(availableCapture)) {
                        possibleKingAdvances.remove(i);
                        hasRemovedAtLeastOnce = true;
                        break;
                    }
                }

                if (hasRemovedAtLeastOnce) {
                    break;
                }
            }
        }

        System.out.println(possibleKingAdvances);

        return possibleKingAdvances;
    }

    private Field createMockFieldWithKingMoveTo(Point src, Advance advance, Advances overallAdvances) throws IllegalPieceMoveException {
        Field mockField = SerializationUtils.clone(getField());

        mockField.get(src).setPossibleAdvances(overallAdvances);
        mockField.move(src, advance);
        return mockField;
    }

    private List<Piece> getOpponentPieces() {
        PlayerType ownPlayerType = getPiece().getPlayerType();
        return getField().getOpponentPiecesBy(ownPlayerType);
    }

    private boolean isUnderCheck() {
        return PiecesFieldModel.getInstance().isUnderCheck(getPiece().getPlayerType());
    }
}
