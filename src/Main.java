import com.panels.CellPanel;
import com.frames.MainFrame;
import com.panels.PaddingPanel;
import com.panels.PiecesPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static final String WINDOW_TITLE = "Chess";
    private static final Dimension FIELD_SIZE = new Dimension(8, 8);
    private static final int CELL_SIZE = 50;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(WINDOW_TITLE);

            JPanel fieldPanel = new PiecesPanel(FIELD_SIZE, CELL_SIZE);
//            JPanel fieldPanel = new CellPanel(FIELD_SIZE, CELL_SIZE);
            JPanel paddingPanel = new PaddingPanel(fieldPanel);

            frame.add(paddingPanel);

            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        });
    }
}
