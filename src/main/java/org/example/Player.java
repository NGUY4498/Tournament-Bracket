package org.example;


/**
 * Each player has a name and a status indicating their current
 * state in the tournament (WINNER, LOSER, ELIMINATED)
 */
public class Player {
    private String playerName;
    private int score;  //idk if I need this. might remove.
    private Status status;

    public Player(String playerName) {
        this.playerName = playerName;
        this.score = 0;
        this.status = Status.WINNER;
    }

    public String getPlayerName() {
        return playerName;
    }
    public int getScore() {
        return score;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
