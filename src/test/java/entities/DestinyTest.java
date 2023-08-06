package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Destiny}.
 * This class contains test cases to verify the behavior of the {@link Destiny} class methods.
 */
class DestinyTest {

    Destiny destiny;

    ArrayList<DestinyCard> destinyCardList;

    ArrayList<DestinyCard> destinyCardListToAdd;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link Destiny} instance and prepares two lists of {@link DestinyCard}.
     */
    @BeforeEach
    void setup(){
        destiny = new Destiny(101);
        destinyCardList = new ArrayList<>(Arrays.asList(
                new DestinyCard("Met a snowstorm and can not leave. Miss a round.", new ArrayList<>(Arrays.asList(0, 0, -1))),
                new DestinyCard("It's time for the FINAL!", new ArrayList<>(Arrays.asList(0, 114, 0)))
        ));
        destinyCardListToAdd= new ArrayList<>(List.of(
                new DestinyCard("Failed a midterm exam. Study harder and miss a turn.", new ArrayList<>(Arrays.asList(0, 0, -1)))
        ));
    }

    /**
     * Test the {@link Destiny#getBlockName()} method to ensure it correctly returns the name of the destiny block.
     */
    @Test
    void testGetBlockName() {
        assertEquals("destiny", destiny.getBlockName());
    }

    /**
     * Test the {@link Destiny#addDestinyCardPool(ArrayList)} method to ensure it adds destiny cards to the pool correctly.
     * Also, verify if the pool contains all the added destiny cards.
     */
    @Test
    void testAddDestinyCardPool() {
        destiny.addDestinyCardPool(destinyCardList);
        assertEquals(destinyCardList, destiny.getDestinyCardPool());

        destiny.addDestinyCardPool(destinyCardListToAdd);
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardList));
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardListToAdd));
    }

    /**
     * Test the {@link Destiny#getDestinyCardPool()} method to ensure it returns the destiny card pool correctly.
     * Verify if the pool is initially empty, and after adding destiny cards, it contains the correct cards.
     */
    @Test
    void testGetDestinyCardPool() {
        assertTrue(destiny.getDestinyCardPool().isEmpty());

        destiny.addDestinyCardPool(destinyCardList);
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardList));

        destiny.addDestinyCardPool(destinyCardListToAdd);
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardListToAdd));
    }
}
