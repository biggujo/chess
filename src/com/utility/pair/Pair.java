package com.utility.pair;

abstract class Pair<T> {
    private final T firstValue;
    private final T secondValue;

    public Pair(T firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirstValue() {
        return firstValue;
    }

    public T getSecondValue() {
        return secondValue;
    }
}
