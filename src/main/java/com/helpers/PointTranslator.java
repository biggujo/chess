package com.helpers;

import java.awt.*;

public class PointTranslator {
    public static Point translate(Point point, int dx, int dy) {
        Point newPoint = point.getLocation();
        newPoint.translate(dx, dy);

        return newPoint;
    }
}
