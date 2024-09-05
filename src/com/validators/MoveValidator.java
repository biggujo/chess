package com.validators;

import com.globals.Defaults;

import java.awt.*;

public class MoveValidator {
    public static boolean validate(Point point) throws ArrayIndexOutOfBoundsException {
        if (isOutOfBoundsBy(point.x) || isOutOfBoundsBy(point.y)) {
            throw new ArrayIndexOutOfBoundsException(point.toString());
        }

        return true;
    }

    private static boolean isOutOfBoundsBy(int number) {
        return number < 0 || number >= Defaults.TILE_AMOUNT;
    }
}
