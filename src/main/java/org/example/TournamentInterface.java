package org.example;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface allows different tournament strategies to be used interchangeably in the same system.
 */
public interface TournamentInterface {


    /**
     * Handles the processing or parsing logic of a tournament round (WILL NOT RUN ALL ROUNDS AT ONCE. JUST ONE.).
     * Managing match pairings and determining which players advance or move to another bracket.
     * @param matches the list of players currently queued for matches
     */
    void processQueue();


    /**
     * Adds players to the tournament from a provided list of player names.
     * @param player a  list of strings containing player names to be added to the tournament
     */
    void addPlayers(ArrayList<String> player);


    /**
     * Determines the winner between two players in a single match.
     * @param playerOne first player competing
     * @param playerTwo second player competing
     */
    void determineWinner(Player playerOne, Player playerTwo);


    /**
     * Invokes the above methods to simulate the progression of the tournament
     */
    void runGame();
}
