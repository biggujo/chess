package com.helpers;

import com.globals.Defaults;

import java.awt.*;

public class IndexCalculatorByPoint {
    public static int getIndex(Point point) {
        return Defaults.TILE_AMOUNT * point.y + point.x;
    }
}
