package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingleElimination implements TournamentInterface{
    List<Player> queue = new ArrayList<>();
    private List<Match> pastMatches = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addPlayers(ArrayList<String> playerNames) {
        for (String player : playerNames) {
            queue.add(new Player(player));
        }
    }

    @Override
    public void processQueue(List<Player> matches) {
        List<Player> tempQueue = new ArrayList<>(matches);
        for(int i = 0; i < tempQueue.size()-1; i+=2) {
            determineWinner(tempQueue.get(i), tempQueue.get(i+1));
        }
    }

    @Override
    public void determineWinner(Player playerOne, Player playerTwo) {
        System.out.println("Who won?" + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName());
        String ans = scanner.nextLine().trim();
        Match match = new Match.Builder()
                .playerA(playerOne)
                .playerB(playerTwo)
                .winner(ans)
                .build();
        queue.remove(ans.equals(playerOne.getPlayerName()) ? playerTwo : playerOne);
        pastMatches.add(match);
    }

    @Override
    public void runGame() {
        while(queue.size() > 1){
            processQueue(queue);
        }
        System.out.println("Winner is " + queue.get(0).getPlayerName());
    }
}