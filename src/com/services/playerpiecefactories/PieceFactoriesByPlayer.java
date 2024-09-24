package com.services.playerpiecefactories;

import com.models.pieces.PlayerType;
import com.view.pieces.BlackPieceFactory;
import com.view.pieces.PieceComponentFactory;
import com.view.pieces.WhitePieceFactory;

import java.util.HashMap;
import java.util.Map;

public class PieceFactoriesByPlayer {
    private static final Map<PlayerType, PieceComponentFactory> playerTypeFactory;

    static {
        playerTypeFactory = new HashMap<>();
        playerTypeFactory.put(PlayerType.FIRST, new BlackPieceFactory());
        playerTypeFactory.put(PlayerType.SECOND, new WhitePieceFactory());
    }

    public static void swapColors() {
        swap();
    }

    public static PieceComponentFactory get(PlayerType playerType) {
        return playerTypeFactory.get(playerType);
    }

    private static void swap() {
        PieceComponentFactory firstFactory = playerTypeFactory.remove(PlayerType.FIRST);
        PieceComponentFactory secondFactory = playerTypeFactory.remove(PlayerType.SECOND);

        playerTypeFactory.put(PlayerType.FIRST, secondFactory);
        playerTypeFactory.put(PlayerType.SECOND, firstFactory);
    }
}
