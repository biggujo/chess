package com.helpers;

import java.util.stream.IntStream;

public class ReverseIntStream {
    public static IntStream rangeClosed(int from, int to) {
        return IntStream.iterate(from, x -> x >= to, x -> x - 1);
    }
}
