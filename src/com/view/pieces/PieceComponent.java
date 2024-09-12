package com.view.pieces;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.attributes.ViewBox;
import com.globals.Defaults;

import javax.swing.*;
import java.awt.*;

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
        SwingUtilities.invokeLater(() -> {
            setOpaque(true);
            setBackground(Defaults.colorScheme.getHighlight());
        });
    }

    public void setInactive() {
        SwingUtilities.invokeLater(() -> {
            setOpaque(false);
            setBackground(null);
        });
    }
}
