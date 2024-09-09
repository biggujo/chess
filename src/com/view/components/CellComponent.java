package com.view.components;

import javax.swing.*;
import java.awt.*;

public class CellComponent extends JPanel {
    public CellComponent(Dimension dimension, Color color) {
        setPreferredSize(dimension);
        setBackground(color);
    }
}
