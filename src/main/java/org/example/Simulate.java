package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Simulate handles the logic of running matches in a double-elimination tournament.
 * It interacts with the user to determine winners, updates player statuses,
 * and runs rounds until the tournament is completed.
 */
public class Simulate {
    BracketController lobby = new BracketController();
    private Scanner scanner = new Scanner(System.in);

    public void addPlayers(ArrayList<String> player) {
        for (String name: player) {
            lobby.addPlayer(name);
        }
        runRound(lobby.getWinnersQueue());
    }

    public void handleMatch(Player playerOne, Player playerTwo) {
        String ans;
        do {
            System.out.println("Who won? " + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName() + "?");
            ans = scanner.nextLine().trim();
            if (!ans.equals(playerOne.getPlayerName()) && !ans.equals(playerTwo.getPlayerName())) {
                System.out.println("Invalid input. Try again.");
            }
        } while (!ans.equals(playerOne.getPlayerName()) && !ans.equals(playerTwo.getPlayerName()));

        Player loser = ans.equals(playerOne.getPlayerName()) ? playerTwo : playerOne;    //Got this from stack post. Its a ternary operator that tests a condition and chooses one of two values..
        if (loser.getStatus() == Status.WINNER) {
            loser.setStatus(Status.LOSER);
            lobby.moveToLosers(loser);
        } else if (loser.getStatus() == Status.LOSER) {
            loser.setStatus(Status.ELIMINATED);
        }
    }

    public void runRound(List<Player> queue) {
        List<Player> matches = new ArrayList<>(queue);
        for (int i = 0; i < matches.size(); i += 2) {
            Player first = matches.get(i);
            Player second = matches.get(i + 1);
            handleMatch(first, second);
        }
        queue.removeIf(player -> player.getStatus() == Status.ELIMINATED);
    }


    /**
     * NEED TO FIGURE OUT HOW RUN LOSERS AND WINNERS QUEUE IN PARALLEL!!!!!!
     */
    public void runGame(){
        int lastGame = 2;
        while(lobby.getWinnersQueue().size()>1){
            runRound(lobby.getWinnersQueue());
            runRound(lobby.getLosersQueue());
        }
    }
}
