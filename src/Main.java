import com.field.FieldPanel;
import com.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static final Dimension FIELD_SIZE = new Dimension(8, 8);
    private static final int CELL_SIZE = 50;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame();

            FieldPanel fieldPanel = new FieldPanel(FIELD_SIZE, CELL_SIZE);
            frame.add(fieldPanel);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
