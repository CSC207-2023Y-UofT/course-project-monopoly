package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StartingPoint}.
 * This class contains test cases to verify the behavior of the {@link StartingPoint} class methods.
 */
class StartingPointTest {
    static StartingPoint startingPoint;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link StartingPoint} instance.
     */
    @BeforeAll
    static void setup(){
        startingPoint = new StartingPoint(100, 1000);
    }

    /**
     * Test the {@link StartingPoint#getBlockName()} method to ensure it correctly returns the name of the StartingPoint block.
     */
    @Test
    void testGetBlockName() {
        assertEquals("startingpoint", startingPoint.getBlockName());
    }

    /**
     * Test case to verify the correctness of the {@link StartingPoint#getBonus()} method.
     * It checks whether the method returns the correct bonus amount set for the StartingPoint block.
     */
    @Test
    void testGetBonus() { assertEquals(1000, StartingPoint.getBonus());}

    /**
     * Test case to verify the correctness of the {@link StartingPoint#setBonus(int)} method.
     * It checks whether the method sets the bonus amount correctly for the StartingPoint block.
     */
    @Test
    void testSetBonus() {
        StartingPoint.setBonus(1100);
        assertEquals(1100, StartingPoint.getBonus());
    }

}