package day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlopeTraversingTest {
    SlopeTraversing mapTraversing;

    @BeforeEach
    public void setUp() {
        mapTraversing = new SlopeTraversing();
    }

    @Test
    public void testThreeRightOneDown() {
        int result = mapTraversing.getNumberOfTreesVisited(3, 1);
        assertEquals(169, result);
    }

    @Test
    public void testOneRightOneDown() {
        int result = mapTraversing.getNumberOfTreesVisited(1, 1);
        assertEquals(87, result);
    }

    @Test
    public void testFiveRightOneDown() {
        int result = mapTraversing.getNumberOfTreesVisited(5, 1);
        assertEquals(99, result);
    }

    @Test
    public void testSevenRightOneDown() {
        int result = mapTraversing.getNumberOfTreesVisited(7, 1);
        assertEquals(98, result);
    }

    @Test
    public void testOneRightTwoDown() {
        int result = mapTraversing.getNumberOfTreesVisited(1, 2);
        assertEquals(53, result);
    }
}
