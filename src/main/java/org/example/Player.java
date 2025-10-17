package org.example;


/**
 * Each player has a name and a status indicating their current
 * state in the tournament (WINNER, LOSER, ELIMINATED)
 */
public class Player {
    private int userID;
    private String playerName;
    private Status status;
    public Player(String playerName) {
        this.playerName = playerName;
        this.status = Status.WINNER;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
