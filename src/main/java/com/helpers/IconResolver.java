package com.helpers;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.parser.SVGLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IconResolver {
    public static SVGDocument resolve(String path) {
        try {
            SVGLoader loader = new SVGLoader();

            URL svgUrl = new File(path).toURI().toURL();
            return loader.load(svgUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
