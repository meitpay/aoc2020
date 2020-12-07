package day5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightSeatFinderTest {
    FlightSeatFinder flightSeatFinder;

    @BeforeEach
    public void setUp() {
        flightSeatFinder = new FlightSeatFinder();
    }

    @Test
    public void partOne() {
        int result = flightSeatFinder.getHighestSeatId();
        assertEquals(885, result);
    }

    @Test
    public void partTwo() {
        int mySeatId = flightSeatFinder.getMySeat();
        assertEquals(623, mySeatId);
    }
}
