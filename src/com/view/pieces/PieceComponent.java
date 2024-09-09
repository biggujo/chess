package com.view.pieces;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.attributes.ViewBox;
import com.github.weisj.jsvg.geometry.size.FloatSize;
import com.globals.MyColors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PieceComponent extends JComponent {
    private final Dimension dimension;
    private final SVGDocument svgDocument;

    public PieceComponent(Dimension dimension, SVGDocument svgDocument) {
        this.dimension = dimension;
        this.svgDocument = svgDocument;

        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (svgDocument == null) {
            super.paintComponent(g);
            return;
        }

        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);

        svgDocument.render(this, (Graphics2D) g, new ViewBox(0, 0, dimension.width, dimension.height));
    }

    public void setActive() {
        setOpaque(true);
        setBackground(MyColors.ACTIVE);
    }

    public void setInactive() {
        setOpaque(false);
        setBackground(null);
    }
}
