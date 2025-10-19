package org.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulateTest {


    @Test
    void oddTotalPlayers() {
        Calculate calc = new Calculate();
        assertEquals(3, calc.numberOfByes(5));
    }

    @Test
    void evenPowerOfTwoByes() {
        Calculate calc = new Calculate();
        assertEquals(0, calc.numberOfByes(4));
    }

    @Test
    void evenNonPowerOfTwoByes() {
        Calculate calc = new Calculate();
        assertEquals(6, calc.numberOfByes(10));
    }

}