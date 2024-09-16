package com.helpers;

import java.io.File;

public enum ApplicationIcons {
    SWAP("swap"),
    LOAD("load"),
    SAVE("save");

    private final String name;

    ApplicationIcons(String name) {
        this.name = "assets" + File.separator + name + ".svg";
    }

    public String getName() {
        return name;
    }
}
