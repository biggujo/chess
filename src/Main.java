import com.controller.PiecesFieldController;
import com.globals.Defaults;
import com.view.ViewManager;
import com.view.frames.MainFrame;
import com.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static final String MAIN_WINDOW_TITLE = "Chess";
    private static final int TILE_AMOUNT = Defaults.TILE_AMOUNT;
    private static final int PIECE_SIZE = Defaults.PIECE_SIZE;

    public static void main(String[] args) throws IOException {
        ViewManager viewManager = ViewManager.getInstance();
        PiecesFieldController.updatePiecesPanel();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame(MAIN_WINDOW_TITLE);

            JPanel panel = new JPanel();

            JPanel piecesPanel = viewManager.getPiecesPanel();
            JPanel fieldSerializationPanel = viewManager.getFieldSerializationPanel();

            JPanel fieldPanel = new CellPanel(PIECE_SIZE, TILE_AMOUNT);

            panel.add(piecesPanel);
            panel.add(viewManager.getAvailableMovesPanel());
            panel.add(fieldPanel);

            panel.setLayout(new OverlayLayout(panel));

            frame.add(new PaddingPanel(panel), BorderLayout.CENTER);
            frame.add(fieldSerializationPanel, BorderLayout.SOUTH);
//            frame.add(new PlayerInfoLabel("second"), BorderLayout.NORTH);
//            frame.add(new PlayerInfoLabel("first"), BorderLayout.SOUTH);
            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        });
    }
}
