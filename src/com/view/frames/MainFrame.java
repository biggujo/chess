package com.view.frames;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(String title) throws HeadlessException {
        super();

        setTitle(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPaddingContentPane();
        setResizable(false);
    }

    private void setPaddingContentPane() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        contentPanel.setBorder(padding);
        setContentPane(contentPanel);
    }
}
