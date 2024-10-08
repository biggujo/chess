import com.controller.PiecesFieldController;
import com.globals.Defaults;
import com.models.pieces.PlayerType;
import com.view.ViewManager;
import com.view.frames.MainFrame;
import com.view.panels.*;
import com.view.panels.wrappers.BorderPanel;
import com.view.panels.wrappers.PaddingPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static final String MAIN_WINDOW_TITLE = "Chess";
    private static final int TILE_AMOUNT = Defaults.TILE_AMOUNT;
    private static final int PIECE_SIZE = Defaults.PIECE_SIZE;

    public static void main(String[] args) throws IOException {
        ViewManager viewManager = ViewManager.getInstance();
        PiecesFieldController.getInstance().updatePiecesPanel();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(MAIN_WINDOW_TITLE);

            JPanel panel = new JPanel();

            JPanel piecesPanel = viewManager.getPiecesPanel();

            JPanel fieldPanel = new CellPanel(PIECE_SIZE, TILE_AMOUNT);

            panel.add(viewManager.getAvailableMovesPanel());
            panel.add(piecesPanel);
            panel.add(fieldPanel);

            panel.setLayout(new OverlayLayout(panel));

            frame.add((JPanel) viewManager.getCapturesPanelBy(PlayerType.SECOND), BorderLayout.SOUTH);
            frame.add((JPanel) viewManager.getCapturesPanelBy(PlayerType.FIRST), BorderLayout.NORTH);
            frame.add(new PaddingPanel(new BorderPanel(panel)), BorderLayout.CENTER);
            frame.add(viewManager.getSettingsPanel(), BorderLayout.EAST);
//            frame.add(new PlayerInfoLabel("second"), BorderLayout.NORTH);
//            frame.add(new PlayerInfoLabel("first"), BorderLayout.SOUTH);
            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        });
    }
}
