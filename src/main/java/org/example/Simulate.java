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

        Player loser = ans.equals(playerOne.getPlayerName()) ? playerTwo : playerOne;    //Got this from stack post. Its a ternary operator that tests a condition and chooses one of two values..
        if (loser.getStatus() == Status.WINNER) {
            loser.setStatus(Status.LOSER);
            lobby.moveToLosers(loser);
        } else if (loser.getStatus() == Status.LOSER) {
            loser.setStatus(Status.ELIMINATED);
        }
    }

    public void runWinnersQueue(List<Player> queue) {
        List<Player> matches = new ArrayList<>(queue);
        for (int i = 0; i < matches.size(); i += 2) {
            Player first = matches.get(i);
            Player second = matches.get(i + 1);
            determineWinner(first, second);
        }
    }

    public void runLosersQueue(List<Player> queue) {
        while(lobby.getLosersQueue().size() > 1){
            Player first = lobby.getLosersQueue().get(0);
            Player second = lobby.getLosersQueue().get(1);
            determineWinner(first, second);
            lobby.getLosersQueue().remove(first.getStatus() == Status.ELIMINATED ? first : second);
        }
    }

    /**
     * NEED TO FIGURE OUT HOW RUN LOSERS AND WINNERS QUEUE IN PARALLEL!!!!!!
     */
    public void lastGame(){
        lobby.moveToWinners(lobby.getLosersQueue().get(0));
        Player playerOne = lobby.getWinnersQueue().get(0);
        Player playerTwo = lobby.getWinnersQueue().get(1);
        String ans;
        int doubleElimination = 2;
        while(doubleElimination > 0){
            System.out.println("Who won? " + playerOne.getPlayerName() + " or " + playerTwo.getPlayerName() + "?");
            ans = scanner.nextLine().trim();
            if(ans.equals(playerTwo.getPlayerName())){
                doubleElimination--;
            }else{
                lobby.getWinnersQueue().remove(playerTwo);
                break;
            }
        }
        if(doubleElimination == 0){
            lobby.getWinnersQueue().remove(playerOne);
        }
    }

    public void runGame(){
        runWinnersQueue(lobby.getWinnersQueue());
        while(lobby.getWinnersQueue().size()>1){
            runWinnersQueue(lobby.getWinnersQueue());
            runLosersQueue(lobby.getLosersQueue());
        }
        lastGame();
        System.out.println("Winner: " + lobby.getWinnersQueue().get(0).getPlayerName());
    }
}
