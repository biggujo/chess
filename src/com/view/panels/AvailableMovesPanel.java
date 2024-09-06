package com.view.panels;

import com.globals.Defaults;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AvailableMovesPanel extends JComponent {
    private static final int CIRCLE_DIAMETER = Defaults.PIECE_SIZE / 5;
    private static final int INITIAL_OFFSET = Defaults.PIECE_SIZE / 2;
    private static final int STEP = Defaults.PIECE_SIZE;
    private static AvailableMovesPanel instance;
    private List<Point> availableMoves;

    public static AvailableMovesPanel getInstance(List<Point> availableMoves) {
        if (AvailableMovesPanel.instance == null) {
            AvailableMovesPanel.instance = new AvailableMovesPanel(availableMoves);
        }

        return AvailableMovesPanel.instance;
    }

    public AvailableMovesPanel(List<Point> availableMoves) {
        this.availableMoves = availableMoves;

        int side = Defaults.PIECE_SIZE * Defaults.TILE_AMOUNT;
        Dimension dimension = new Dimension(side, side);
        setPreferredSize(dimension);

        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setBackground(Color.LIGHT_GRAY);

        for (Point p : availableMoves) {
            fillOvalAt(g2d, p);
        }
    }

    public void setAvailableMoves(List<Point> availableMoves) {
        this.availableMoves = availableMoves;
        revalidate();
        repaint();
    }

    private void fillOvalAt(Graphics2D g2d, Point coordinates) {
        int x = INITIAL_OFFSET + coordinates.x * STEP - (CIRCLE_DIAMETER / 2);
        int y = INITIAL_OFFSET + coordinates.y * STEP - (CIRCLE_DIAMETER / 2);

        g2d.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }
}
