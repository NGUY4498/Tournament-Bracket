package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * Handles the logic of running matches in a double-elimination tournament. It interacts with the user to determine winners,
 * updates player statuses, and runs rounds until the tournament is completed.
 */
public class DoubleElimination implements TournamentInterface {
    private Scanner scanner = new Scanner(System.in);
    private List<Player> winnersQueue = new ArrayList<>();
    private List<Player> losersQueue = new ArrayList<>();

    @Override
    public void addPlayers(ArrayList<String> playerNames) {
        for (String name : playerNames) {
            winnersQueue.add(new Player(name));
        }
    }

    @Override
    public void determineWinner(Player playerOne, Player playerTwo) {
        String ans;
        do {
            System.out.println("Who won? " + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName() + "?");
            ans = scanner.nextLine().trim();
            if (!ans.equals(playerOne.getPlayerName()) && !ans.equals(playerTwo.getPlayerName())) {
                System.out.println("Invalid input. Try again.");
            }
        } while (!ans.equals(playerOne.getPlayerName()) && !ans.equals(playerTwo.getPlayerName()));
        Player loser = ans.equals(playerOne.getPlayerName()) ? playerTwo : playerOne;
        switch (loser.getStatus()) {
            case WINNER:
                loser.setStatus(Status.LOSER);
                winnersQueue.remove(loser);
                losersQueue.add(loser);


                Match match = new Match.Builder()
                        .playerA(playerOne)
                        .playerB(playerTwo)
                        .bracketStatus(Status.WINNER)
                        .build();
                break;
            case LOSER:
                loser.setStatus(Status.ELIMINATED);
                losersQueue.remove(loser);
                break;
        }
    }


    @Override
    public void processQueue(List<Player> queue) {
        List<Player> tempQueue = new ArrayList<>(queue);
        for (int i=0; i < tempQueue.size()-1;i+=2) {
            determineWinner(tempQueue.get(i), tempQueue.get(i+1));
        }
    }

    @Override
    public void runGame() {
        while (winnersQueue.size() > 1) {
            processQueue(winnersQueue);
            processQueue(losersQueue);
        }

        winnersQueue.add(losersQueue.get(0));
        int doubleElimination = 2;
        while (winnersQueue.size() > 1) {
            Player playerOne = winnersQueue.get(0);
            Player playerTwo = winnersQueue.get(1);

            System.out.println("Who won? " + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName() + "?");
            String ans = scanner.nextLine().trim();

            if (ans.equals(playerTwo.getPlayerName())) {
                doubleElimination--;
                if (doubleElimination == 0) {
                    winnersQueue.remove(playerOne);
                }
            } else if (ans.equals(playerOne.getPlayerName())) {
                winnersQueue.remove(playerTwo);
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
        System.out.println("Winner: " + winnersQueue.get(0).getPlayerName());
    }
}