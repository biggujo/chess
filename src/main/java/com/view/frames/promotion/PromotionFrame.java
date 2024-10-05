package com.view.frames.promotion;

import com.models.pieces.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PromotionFrame extends JDialog {
    private final PlayerType playerType;
    private final Point coordinates;

    public PromotionFrame(PlayerType playerType, Point coordinates) throws HeadlessException {
        this.playerType = playerType;
        this.coordinates = coordinates;

        initialize();
    }

    private void initialize() {
        setVerticalBoxLayout();
        addLabel();

        addPossiblePromotionVariants();

        applyPreferences();
    }

    private void setVerticalBoxLayout() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    private void addLabel() {
        getContentPane().add(new JLabel("Choose a piece to promote:"));
    }

    private void addPossiblePromotionVariants() {
        PieceChooseComponent.Builder pieceChooseBuilder = createBasicPredefinedBuilder();

        new PromotionMouseListeners().apply(this)
                .forEach(pieceChooseBuilder::addMouseListener);

        Arrays.stream(AvailablePieceTypes.TYPES).forEach(t -> {
            PieceChooseComponent component = pieceChooseBuilder
                    .pieceType(t)
                    .build();

            getContentPane().add(component);
        });
    }

    private PieceChooseComponent.Builder createBasicPredefinedBuilder() {
        return new PieceChooseComponent.Builder()
                .coordinates(coordinates)
                .playerType(playerType);
    }

    private void applyPreferences() {
        setMinimumSize(new Dimension(250, 210));
        setResizable(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
    }
}
