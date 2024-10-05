package com.models.playerstatus;

import com.models.pieces.PlayerType;

import java.io.Serial;
import java.io.Serializable;

public class PlayerStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private PlayerType currentPlayer;
    private PlayerType playerUnderCheck;

    public static PlayerStatus fromInitialPlayer(PlayerType initialPlayer) {
        return new PlayerStatus(initialPlayer);
    }

    private PlayerStatus(PlayerType currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {
        switch (currentPlayer) {
            case FIRST -> this.currentPlayer = PlayerType.SECOND;
            case SECOND -> this.currentPlayer = PlayerType.FIRST;
        }
    }

    public PlayerType getPlayerUnderCheck() {
        return playerUnderCheck;
    }

    public void setCheckFor(PlayerType playerType) {
        playerUnderCheck = playerType;
    }

    public void clearCheck() {
        playerUnderCheck = null;
    }

    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        PlayerStatus playerStatus1 = new PlayerStatus(PlayerType.FIRST);

        playerStatus1.switchPlayer();

        System.out.println(playerStatus1.getCurrentPlayer());

        playerStatus1.switchPlayer();

        System.out.println(playerStatus1.getCurrentPlayer());
    }
}
