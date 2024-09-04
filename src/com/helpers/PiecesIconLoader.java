package com.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PiecesIconLoader {
    public static Image fetchImageFrom(PiecesIconPaths path) {
        try {
            return ImageIO.read(new File(path.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
