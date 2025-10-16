package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This Single Eliminate class currently assumes even number of players.
 */
public class SingleEliminate implements TournamentInterface {

    public List<Player> queue = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    ArrayList<String> playersTest = new ArrayList<>(Arrays.asList("Tom","Jack","Sam","Ryan"));

    @Override
    public void processQueue(List<Player> matches) {
        List<Player> tempQueue = new ArrayList<>(matches);
        for(int i = 0; i < tempQueue.size(); i+=2){
            determineWinner(tempQueue.get(i), tempQueue.get(i+1));
        }

    }

    @Override
    public void addPlayers(ArrayList<String> player) {
        for(String p : player){
            queue.add(new Player(p));
        }
    }

    @Override
    public void determineWinner(Player playerOne, Player playerTwo) {
        System.out.println("Who won?" + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName());
        String ans = scanner.nextLine().trim();
        //if answer matches playerOne name, remove Player two from queue
        // queue.remove(playerTwo);
        //} else{
        //    queue.remove(playerOne);
        //}

        queue.remove(ans.equals(playerOne.getPlayerName()) ? playerTwo : playerOne);
    }

    @Override
    public void runGame() {
        while(queue.size() > 1){
            processQueue(queue);
        }
        System.out.println("Winner is " + queue.get(0).getPlayerName());
    }
}
