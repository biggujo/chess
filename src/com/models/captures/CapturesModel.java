package com.models.captures;

import com.models.pieces.abstractpiece.Piece;
import com.services.playerpiecefactories.PieceFactoriesByPlayer;
import com.view.pieces.PieceComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CapturesModel {
    private final Dimension PIECE_DIMENSION = new Dimension(16, 16);
    private List<Piece> pieces = new ArrayList<>();

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public int getTotalValue() {
        return pieces.stream().mapToInt(Piece::getValue).sum();
    }

    public List<JComponent> getComponents() {
        return pieces.stream().map(p -> {
            PieceComponentFactory factory = PieceFactoriesByPlayer.get(p.getPlayerType());
            return factory.getInstance(PIECE_DIMENSION, p);
        }).toList();
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
}
