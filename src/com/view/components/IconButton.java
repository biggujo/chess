package com.view.components;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.attributes.ViewBox;
import com.globals.Defaults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public abstract class IconButton extends JComponent {
    private static final int SIZE = Defaults.ICON_BUTTON_SIZE;

    private final SVGDocument svgDocument;

    public IconButton(SVGDocument svgDocument, MouseListener listener) {
        this.svgDocument = svgDocument;
        setPreferredSize(new Dimension(SIZE, SIZE));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        svgDocument.render(this, (Graphics2D) g, new ViewBox(0, 0, SIZE, SIZE));
        g.dispose();
    }
}
