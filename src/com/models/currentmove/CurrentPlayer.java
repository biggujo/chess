package com.models.currentmove;

import com.models.pieces.PlayerType;

public class CurrentPlayer {
    private PlayerType currentPlayer;

    public CurrentPlayer(PlayerType currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {
        switch (currentPlayer) {
            case FIRST -> this.currentPlayer = PlayerType.SECOND;
            case SECOND -> this.currentPlayer = PlayerType.FIRST;
        }
    }

    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        CurrentPlayer currentPlayer1 = new CurrentPlayer(PlayerType.FIRST);

        currentPlayer1.switchPlayer();

        System.out.println(currentPlayer1.getCurrentPlayer());

        currentPlayer1.switchPlayer();

        System.out.println(currentPlayer1.getCurrentPlayer());
    }
}
