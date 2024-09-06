package com.helpers;

public enum IconPaths {
    PAWN_WHITE("assets/pawn-white.png"),
    PAWN_BLACK("assets/pawn-black.png");

    private final String path;

    IconPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
