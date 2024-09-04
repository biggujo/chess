import com.view.frames.MainFrame;
import com.view.panels.CellPanel;
import com.view.panels.PaddingPanel;
import com.view.panels.PiecesPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static final String WINDOW_TITLE = "Chess";
    private static final Dimension FIELD_SIZE = new Dimension(8, 8);
    private static final int CELL_SIZE = 50;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(WINDOW_TITLE);

            JPanel panel = new JPanel();

            JPanel piecesPanel = null;
            try {
                piecesPanel = PiecesPanel.getInstance();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JPanel fieldPanel = null;
            try {
                fieldPanel = new CellPanel(FIELD_SIZE, CELL_SIZE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            panel.add(piecesPanel);
            panel.add(fieldPanel);

            panel.setLayout(new OverlayLayout(panel));

            frame.add(new PaddingPanel(panel));

            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        });
    }
}
