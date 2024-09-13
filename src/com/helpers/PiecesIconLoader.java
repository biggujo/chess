package com.helpers;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.parser.SVGLoader;
import com.services.iconpathresolvers.IconPathsStorage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PiecesIconLoader {
    public static SVGDocument fetchImageFrom(String path) {
        try {
            SVGLoader loader = new SVGLoader();

            URL svgUrl = new File(path).toURI().toURL();
            return loader.load(svgUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
