package com.view.choosers;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ChessFileChooser extends JFileChooser {
    private static final String EXTENSION = "chess";

    public ChessFileChooser() {
        setFileFilter(new FileNameExtensionFilter("Default extension", EXTENSION));
    }

    public File load() {
        int result = showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            throw new NullPointerException();
        }

        return getSelectedFile();
    }

    public File save() {
        int result = showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            throw new NullPointerException();
        }

        File file = getSelectedFile();
        String absolutePath = file.getAbsolutePath();

        if (!absolutePath.endsWith("." + EXTENSION)) {
            file = new File(absolutePath + "." + EXTENSION);
        }

        return file;
    }
}
