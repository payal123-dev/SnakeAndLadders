package org.example.game.Entity;

public class Player {

    private final int playerId;
    private final String playerName;
    private int currentPosition;

    public Player(int playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.currentPosition=0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
