package org.example;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Simulate simulate = new Simulate();
        ArrayList<String> players = new ArrayList<>(Arrays.asList("Tom","Jack","Sam","Ryan")); //Test case. Feel free to add more players.But it must be 2^n size!!!!
        simulate.addPlayers(players);
        simulate.runGame();
    }
}