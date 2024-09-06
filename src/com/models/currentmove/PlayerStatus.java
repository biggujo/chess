package com.models.currentmove;

import com.models.pieces.PlayerType;

public class PlayerStatus {
    private PlayerType currentPlayer;

    public static PlayerStatus fromInitialPlayer(PlayerType initialPlayer) {
        return new PlayerStatus(initialPlayer);
    }

    private PlayerStatus(PlayerType currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {
        switch (currentPlayer) {
            case WHITE -> this.currentPlayer = PlayerType.BLACK;
            case BLACK -> this.currentPlayer = PlayerType.WHITE;
        }
    }

    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        PlayerStatus playerStatus1 = new PlayerStatus(PlayerType.WHITE);

        playerStatus1.switchPlayer();

        System.out.println(playerStatus1.getCurrentPlayer());

        playerStatus1.switchPlayer();

        System.out.println(playerStatus1.getCurrentPlayer());
    }
}
