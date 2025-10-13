package org.example;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface allows different tournament strategies to be used interchangeably in the same system.
 */
public interface TournamentInterface {
    void processQueue(List<Player> matches);

    void addPlayers(ArrayList<String> player);

    void determineWinner(Player playerOne, Player playerTwo);

    void runGame();
}
