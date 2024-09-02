package com.utility.pair;

public class Offset extends Pair<Integer> {
    public Offset(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return getFirstValue();
    }

    public int getY() {
        return getSecondValue();
    }
}
