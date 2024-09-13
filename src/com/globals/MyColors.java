package com.globals;

import java.awt.*;

class MyColors implements ColorScheme {
    //    public static Color WHITE = new Color(232, 237, 249);
//    public static Color BLACK = new Color(183, 192, 216);
//    public static Color ACTIVE = new Color(171, 163, 240);
    private static final Color LIGHT = Color.WHITE;
    private static final Color DARK = Color.BLACK;
    private static final Color HIGHLIGHT = Color.YELLOW;

    @Override
    public Color getDark() {
        return MyColors.LIGHT;
    }

    @Override
    public Color getLight() {
        return MyColors.DARK;
    }

    @Override
    public Color getHighlight() {
        return MyColors.HIGHLIGHT;
    }
}
