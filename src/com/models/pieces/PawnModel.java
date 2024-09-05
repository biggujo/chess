package com.models.pieces;

import com.models.piecesfieldmodel.PiecesFieldModel;
import com.validators.MoveValidator;
import com.view.pieces.PieceType;

import java.awt.*;

public class PawnModel extends PieceModelImpl {
    private boolean hasMovedAtLeastOnce = false;

    public PawnModel(PieceType[][] field, Point coordinates) {
        super(field, coordinates);
    }

    public void addAvailableMoves() {
        if (hasReachedTheTop()) {
            return;
        }

        addSingleForward();
        addDoubleForward();
    }

    private boolean hasReachedTheTop() {
        return getCoordinates().y == 0;
    }

    private void addDoubleForward() {
        if (hasMovedAtLeastOnce) {
            return;
        }

        addMoveToTopBy(2);
    }

    private void addSingleForward() {
        addMoveToTopBy(1);
    }

    private void addMoveToTopBy(int y) {
        Point doubleForwardDestination = getCoordinates().getLocation();
        doubleForwardDestination.translate(0, -y);

        tryToAddAvailableMove(doubleForwardDestination);
    }

    private void tryToAddAvailableMove(Point coordinates) {
        try {
            addAvailableMove(coordinates);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void addAvailableMove(Point coordinates) throws ArrayIndexOutOfBoundsException {
        if (MoveValidator.validate(coordinates)) {
            getAvailableMoves().add(coordinates);
        }
    }

    @Override
    public void setCoordinates(Point coordinates) throws ArrayIndexOutOfBoundsException {
        super.setCoordinates(coordinates);
        hasMovedAtLeastOnce = true;
    }

    public static void main(String[] args) {
        Point currentCoordinates = new Point(2, 3);
        PieceType[][] field = PiecesFieldModel.getFieldTypes();
        PawnModel pawnModel = new PawnModel(field, currentCoordinates);

        System.out.println(pawnModel.getAvailableMoves());
    }
}
