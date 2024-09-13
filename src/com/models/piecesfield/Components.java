package com.models.piecesfield;

import com.services.playerpiecefactories.PieceFactoriesByPlayer;
import com.view.pieces.EmptyPieceComponent;
import com.view.pieces.PieceComponentFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Components {
    private final ArrayList<JComponent> components;

    public static Components from(Field field) {
        Field field1 = new Field(field.getList());
        field1.getList().sort(Comparable::compareTo);

        return new Components(field1);
    }

    private Components(Field field) {
        components = new ArrayList<>();

        field.getList().forEach(pieceModel -> {
            PieceComponentFactory factory = PieceFactoriesByPlayer.get(pieceModel.getPlayerType());

            if (factory == null) {
                components.add(new EmptyPieceComponent());
                return;
            }

            JComponent pieceInstance = factory.getInstance(pieceModel);
            components.add(pieceInstance);
        });
    }

    public JComponent get(int index) {
        return components.get(index);
    }

    public boolean add(JComponent jComponent) {
        return components.add(jComponent);
    }

    public List<JComponent> getList() {
        return components;
    }
}
