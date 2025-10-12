package org.example;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SimulateTest {
    @Test
    void testSimulateLoadPlayers() {
        Simulate simulate = new Simulate();
        ArrayList<String> players = new ArrayList<>(Arrays.asList("Tom","Jack","Sam","Ryan"));
        simulate.addPlayers(players);
        assertEquals(4, simulate.lobby.getWinnersQueue().size());
    }
}