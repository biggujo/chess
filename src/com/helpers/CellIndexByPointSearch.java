package com.helpers;

import com.globals.Defaults;

import java.awt.*;

public class CellIndexByPointSearch {
    public static Point getIndex(Point point) {
        int x = point.x / Defaults.PIECE_SIZE;
        int y = point.y / Defaults.PIECE_SIZE;
        return new Point(x, y);
    }
}
