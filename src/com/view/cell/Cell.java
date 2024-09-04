package com.view.cell;

import javax.swing.*;
import java.awt.*;

public abstract class Cell extends JPanel {
    public Cell(Dimension dimension) {
        setPreferredSize(dimension);
    }
}
