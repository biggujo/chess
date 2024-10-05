package com.view.frames.promotion;

import com.controller.PiecesFieldController;
import com.models.pieces.PieceType;
import com.models.pieces.PlayerType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PromotionMouseAdapter extends MouseAdapter {
    private final PlayerType playerType;
    private final PieceType pieceType;
    private final Point coordinates;

    private PromotionMouseAdapter(Builder builder) {
        this.playerType = builder.playerType;
        this.pieceType = builder.pieceType;
        this.coordinates = builder.coordinates;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        PiecesFieldController.getInstance().replacePieceAt(coordinates, playerType, pieceType);
    }

    static class Builder {
        private PlayerType playerType;
        private PieceType pieceType;
        private Point coordinates;

        public Builder playerType(PlayerType playerType) {
            this.playerType = playerType;
            return this;
        }

        public Builder pieceType(PieceType pieceType) {
            this.pieceType = pieceType;
            return this;
        }

        public Builder coordinates(Point coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public PromotionMouseAdapter build() {
            if (coordinates == null) {
                throw new IllegalStateException();
            }

            if (playerType == null) {
                throw new IllegalStateException();
            }

            if (pieceType == null) {
                throw new IllegalStateException();
            }

            return new PromotionMouseAdapter(this);
        }
    }
}
