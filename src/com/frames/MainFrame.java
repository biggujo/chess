package com.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final String TITLE = "Chess";
    private static final Dimension DIMENSION = new Dimension(400, 300);

    public MainFrame() throws HeadlessException {
        super();

        setTitle(TITLE);
        setSize(DIMENSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
