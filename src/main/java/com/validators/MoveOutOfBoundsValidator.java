package com.validators;

import com.globals.Defaults;
import com.models.pieces.IllegalPieceMoveException;

import java.awt.*;

public class MoveOutOfBoundsValidator {
    public static boolean validateDestination(Point point) throws IllegalPieceMoveException {
        if (isOutOfBoundsBy(point.x) || isOutOfBoundsBy(point.y)) {
            throw new IllegalPieceMoveException();
        }

        return true;
    }

    private static boolean isOutOfBoundsBy(int number) {
        return number < 0 || number >= Defaults.TILE_AMOUNT;
    }
}
