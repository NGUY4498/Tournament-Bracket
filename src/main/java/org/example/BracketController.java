package org.example;
import java.util.ArrayList;
import java.util.List;


/**
 * BracketController manages the tournament queues for a double-elimination tournament.
 * It keeps track of winners and losers queues, and handles moving players between them.
 */
public class BracketController {
    private List<Player> winnersQueue = new ArrayList<Player>();
    private List<Player> losersQueue = new ArrayList<Player>();

    public void addPlayer(String name){
        winnersQueue.add(new Player(name));
    }

    public void moveToLosers(Player player){
        winnersQueue.remove(player);
        losersQueue.add(player);
        player.setStatus(Status.LOSER);
    }

    public void moveToWinners(Player player){
        winnersQueue.add(player);
        losersQueue.remove(player);
    }

    public List<Player> getWinnersQueue(){
        return winnersQueue;
    }

    public List<Player> getLosersQueue(){
        return losersQueue;
    }
}
