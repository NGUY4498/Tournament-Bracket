package org.example;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        /**UPDATE TO RUN A DIFFERENT TOURNAMENT**/
        SingleElimination simulate = new SingleElimination();

        /**UPDATE THIS LIST TO ADD OR REMOVE PLAYERS**/
        ArrayList<String> players = new ArrayList<>(Arrays.asList("Tom","Jack","Sam","Ryan"));

        simulate.addPlayers(players);
        simulate.runGame();
    }
}