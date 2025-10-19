package org.example;



public class Calculate {
    /**
     * This function calculates the number of byes of a tournament.
     * Equation: 2^(Log2(n)) - total players.
     * Where n is the total of players.
     * @param totalPlayers the total amount of players in a single tournament
     * @return the total byes
     */
    public int numberOfByes(int totalPlayers){
        return (int) (Math.pow(2, Math.ceil(Math.log(totalPlayers) / Math.log(2))) - totalPlayers);
    }

    /**
     * This function calculates the number of games of a tournament
     * @param totalPlayers
     * @return
     */
    public int totalGames(int totalPlayers){

        return 0;
    }
}
