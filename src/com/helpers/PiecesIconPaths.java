package com.helpers;

public enum PiecesIconPaths {
    PAWN("assets/pawn.png");

    private final String path;

    PiecesIconPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
