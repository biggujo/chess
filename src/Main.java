import com.panels.FieldPanel;
import com.frames.MainFrame;
import com.panels.PaddingPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static final String WINDOW_TITLE = "Chess";
    private static final Dimension FIELD_SIZE = new Dimension(6, 9);
    private static final int CELL_SIZE = 50;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(WINDOW_TITLE);

            JPanel fieldPanel = new FieldPanel(FIELD_SIZE, CELL_SIZE);
            JPanel paddingPanel = new PaddingPanel(fieldPanel);

            frame.add(paddingPanel);

            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        });
    }
}
