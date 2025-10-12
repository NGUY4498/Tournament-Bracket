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
    }

    public void determineWinner(Player playerOne, Player playerTwo) {
        String ans;
        do {
            System.out.println("Who won? " + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName() + "?");
            ans = scanner.nextLine().trim();
            if (!ans.equals(playerOne.getPlayerName()) && !ans.equals(playerTwo.getPlayerName())) {
                System.out.println("Invalid input. Try again.");
            }
        } while (!ans.equals(playerOne.getPlayerName()) && !ans.equals(playerTwo.getPlayerName()));
        Player loser = ans.equals(playerOne.getPlayerName()) ? playerTwo : playerOne;    //ternary operator that tests a condition and chooses one of two values based on the condition (found this online lol)
        if (loser.getStatus() == Status.WINNER) {
            loser.setStatus(Status.LOSER);
            lobby.moveToLosers(loser);
        } else if (loser.getStatus() == Status.LOSER) {
            lobby.getLosersQueue().remove(loser);
        }
    }

    public void runQueue(List<Player> queue, boolean isWinnersQueue) {
        if (isWinnersQueue) {
            List<Player> matches = new ArrayList<>(queue);       //might remove this but wanted to avoid iterating issues...
            for (int i = 0; i < matches.size() - 1; i += 2) {
                Player first = matches.get(i);
                Player second = matches.get(i + 1);
                determineWinner(first, second);
            }                                               //merged runLosers and runWinners into runQueue to make it easier to read...
        } else {
            while (queue.size() > 1) {
                Player first = queue.get(0);
                Player second = queue.get(1);
                determineWinner(first, second);
                Player loser = (first.getStatus() == Status.ELIMINATED) ? first : second;
                queue.remove(loser);
            }
        }
    }


    public void runGame() {
        while (lobby.getWinnersQueue().size() > 1) {
            runQueue(lobby.getWinnersQueue(),true);
            runQueue(lobby.getLosersQueue(),false);
        }
        lobby.moveToWinners(lobby.getLosersQueue().get(0));
        int doubleElimination = 2;
        while (doubleElimination > 0) {
            Player playerOne = lobby.getWinnersQueue().get(0);
            Player playerTwo = lobby.getWinnersQueue().get(1);
            System.out.println("Who won? " + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName() + "?");
            String ans = scanner.nextLine().trim();

            if (ans.equals(playerTwo.getPlayerName())) {
                doubleElimination--;
                if (doubleElimination == 0) {
                    lobby.getWinnersQueue().remove(playerOne);
                }
            } else if (ans.equals(playerOne.getPlayerName())) {
                lobby.getWinnersQueue().remove(playerTwo);
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
        System.out.println("Winner: " + lobby.getWinnersQueue().get(0).getPlayerName());
    }
}
