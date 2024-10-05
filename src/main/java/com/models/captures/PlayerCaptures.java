package com.models.captures;

import com.models.pieces.PlayerType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerCaptures {
    private final Map<PlayerType, CapturesModel> capturesMap;

    public PlayerCaptures() {
        this.capturesMap = new HashMap<>();
        addPlayerCapturesByType(PlayerType.values());
    }

    public CapturesModel get(PlayerType playerType) {
        return capturesMap.get(playerType);
    }

    protected void addPlayerCapturesByType(PlayerType[] playerTypes) {
        Arrays.stream(playerTypes).forEach(t -> capturesMap.put(t, new CapturesModel()));
    }
}
