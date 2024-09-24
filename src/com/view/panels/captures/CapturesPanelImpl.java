package com.view.panels.captures;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CapturesPanelImpl extends JPanel implements CapturesPanel {
    private final JLabel label;
    private final JPanel captures;

    public CapturesPanelImpl() {
        setPreferredSize(new Dimension(0, 25));
        label = new JLabel("Score: 0");
        captures = new JPanel();

        add(captures);
        add(label);
    }

    @Override
    public void setScore(int score) {
        label.setText("Score: " + score);
    }

    public void updateCapturesWith(List<JComponent> components) {
        captures.removeAll();

        for (JComponent o : components) {
            captures.add(o);
        }
    }
}
