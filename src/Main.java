import com.globals.Defaults;
import com.view.frames.MainFrame;
import com.view.labels.PlayerInfoLabel;
import com.view.panels.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static final String MAIN_WINDOW_TITLE = "Chess";
    private static final Dimension FIELD_SIZE = new Dimension(8, 8);
    private static final int CELL_SIZE = Defaults.PIECE_SIZE;

    public static void main(String[] args) throws IOException {
        PanelManager panelManager = PanelManager.getInstance();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(MAIN_WINDOW_TITLE);

            JPanel panel = new JPanel();

            JPanel piecesPanel = panelManager.getPiecesPanel();
            JPanel fieldPanel = null;
            try {
                fieldPanel = new CellPanel(FIELD_SIZE, CELL_SIZE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            panel.add(piecesPanel);
            panel.add(panelManager.getAvailableMovesPanel());
            panel.add(fieldPanel);

            panel.setLayout(new OverlayLayout(panel));

            frame.add(new PaddingPanel(panel), BorderLayout.CENTER);
            frame.add(new PlayerInfoLabel("second"), BorderLayout.NORTH);
            frame.add(new PlayerInfoLabel("first"), BorderLayout.SOUTH);
            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        });
    }
}
