package com.helpers;

import java.io.File;

public enum ApplicationIcons {
    SWAP("swap"),
    LOAD("load"),
    SAVE("save"),
    RESET("reset");

    private final String name;

    ApplicationIcons(String name) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("src");
        stringBuilder.append(File.separator);

        stringBuilder.append("main");
        stringBuilder.append(File.separator);

        stringBuilder.append("resources");
        stringBuilder.append(File.separator);

        stringBuilder.append(name);
        stringBuilder.append(".svg");

        this.name = stringBuilder.toString();
    }

    public String getName() {
        return name;
    }
}
