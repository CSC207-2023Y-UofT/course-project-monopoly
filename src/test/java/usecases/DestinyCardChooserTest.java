package usecases;

import entities.Destiny;
import entities.DestinyCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DestinyCardChooser}.
 */
class DestinyCardChooserTest {

    static Destiny destiny;

    /**
     * Setup method to initialize test data.
     */
    @BeforeAll
    static void setup(){
        destiny = new Destiny(101);
    }

    /**
     * Test case for choosing a card from a non-empty card pool.
     * It prepares a test data card pool, adds it to the Destiny instance, and then calls the chooseCard method.
     * The test verifies that a non-null card is chosen from the card pool.
     */
    @Test
    void testChooseCardFromNonEmptyCardPool(){
        ArrayList<DestinyCard> cardPool = new ArrayList<>(Arrays.asList(
                new DestinyCard("Met a snowstorm and can not leave. Miss a round.", new ArrayList<>(Arrays.asList(0, 0, -1))),
                new DestinyCard("It's time for the FINAL!", new ArrayList<>(Arrays.asList(0, 114, 0)))
        ));
        destiny.addDestinyCardPool(cardPool);

        // Test the chooseCard method
        DestinyCard chosenCard = DestinyCardChooser.chooseCard(destiny);

        // Verify that a non-null card is chosen from the card pool
        assertNotNull(chosenCard);
        assertTrue(cardPool.contains(chosenCard));
    }

    /**
     * Test case for choosing a card from an empty card pool.
     * It calls the chooseCard method when the card pool is empty and verifies that null is returned.
     */
    @Test
    void testChooseCardFromEmptyCardPool(){
        // Test the chooseCard method with an empty card pool
        DestinyCard chosenCard = DestinyCardChooser.chooseCard(destiny);

        // Verify that null is returned when the card pool is empty
        assertNull(chosenCard);
    }


}