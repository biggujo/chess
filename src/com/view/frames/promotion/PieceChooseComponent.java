package com.view.frames.promotion;

import com.globals.Defaults;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;
import com.services.playerpiecefactories.PieceFactoriesByPlayer;
import com.view.pieces.PieceComponentFactory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

class PieceChooseComponent extends JPanel {
    private static final int PADDING = 4;
    private static final Dimension PIECE_DIMENSION = new Dimension(24, 24);
    private final Point coordinates;
    private final PlayerType playerType;
    private final PieceType pieceType;
    private final List<MouseListener> chooseAdapters;

    private PieceChooseComponent(Builder builder) {
        this.coordinates = builder.coordinates;
        this.playerType = builder.playerType;
        this.pieceType = builder.pieceType;
        this.chooseAdapters = builder.chooseAdapters;

        initialize();
    }

    private void initialize() {
        addPiece();
        addLabel();

        applyPreferences();

        addListeners();
    }

    private void addPiece() {
        add(createPiece());
    }

    private void addLabel() {
        add(new JLabel(pieceType.name()));
    }

    private JComponent createPiece() {
        PieceComponentFactory pieceFactory = PieceFactoriesByPlayer.get(playerType);
        return pieceFactory.getInstance(PIECE_DIMENSION, pieceType);
    }

    private void applyPreferences() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBorder();
    }

    private void addListeners() {
        addPromotionListener();
        addProvidedListeners();
    }

    private void addPromotionListener() {
        MouseAdapter promotionAdapter = new PromotionMouseAdapter.Builder()
                .coordinates(coordinates)
                .pieceType(pieceType)
                .playerType(playerType)
                .build();

        addMouseListener(promotionAdapter);
    }

    private void addProvidedListeners() {
        chooseAdapters.forEach(this::addMouseListener);
    }

    private void addBorder() {
        Border paddingBorder = BorderFactory.createEmptyBorder(PieceChooseComponent.PADDING,
                PieceChooseComponent.PADDING,
                PieceChooseComponent.PADDING,
                PieceChooseComponent.PADDING);
        Border darkBorder = BorderFactory.createLineBorder(Defaults.colorScheme.getDark());

        CompoundBorder compoundBorder = new CompoundBorder(paddingBorder, darkBorder);

        setBorder(compoundBorder);
    }

    static class Builder {
        private Point coordinates;
        private PlayerType playerType;
        private PieceType pieceType;
        private final List<MouseListener> chooseAdapters = new ArrayList<>();

        public Builder coordinates(Point coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder playerType(PlayerType playerType) {
            this.playerType = playerType;
            return this;
        }

        public Builder pieceType(PieceType pieceType) {
            this.pieceType = pieceType;
            return this;
        }

        public Builder addMouseListener(MouseListener chooseAdapter) {
            this.chooseAdapters.add(chooseAdapter);
            return this;
        }

        public PieceChooseComponent build() {
            if (coordinates == null) {
                throw new IllegalStateException();
            }

            if (playerType == null) {
                throw new IllegalStateException();
            }

            if (pieceType == null) {
                throw new IllegalStateException();
            }

            return new PieceChooseComponent(this);
        }
    }
}
