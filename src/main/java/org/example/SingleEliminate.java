package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This Single Eliminate class currently assumes even number of players.
 */
public class SingleEliminate implements TournamentInterface {

    public List<String> queue = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    ArrayList<String> playersTest = new ArrayList<>(Arrays.asList("Tom","Jack","Sam","Ryan"));

    @Override
    public void processQueue(List<Player> matches) {

    }

    @Override
    public void addPlayers(ArrayList<String> player) {
        for(String p : player){
            queue.add(p);
        }
    }

    @Override
    public void determineWinner(Player playerOne, Player playerTwo) {

    }

    @Override
    public void runGame() {

    }
}
