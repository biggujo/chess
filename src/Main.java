import com.globals.Defaults;
import com.view.frames.MainFrame;
import com.view.labels.PlayerInfoLabel;
import com.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static final String MAIN_WINDOW_TITLE = "Chess";
    private static final int TILE_AMOUNT = Defaults.TILE_AMOUNT;
    private static final int PIECE_SIZE = Defaults.PIECE_SIZE;

    public static void main(String[] args) throws IOException {
        PanelManager panelManager = PanelManager.getInstance();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(MAIN_WINDOW_TITLE);

            JPanel panel = new JPanel();

            JPanel piecesPanel = panelManager.getPiecesPanel();


            JPanel fieldPanel = new CellPanel(PIECE_SIZE, TILE_AMOUNT);

            panel.add(piecesPanel);
//            panel.add(panelManager.getAvailableMovesPanel());
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
