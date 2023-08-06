package entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DestinyCard}.
 * This class contains test cases to verify the behavior of the {@link DestinyCard} class methods.
 */
class DestinyCardTest {

    static DestinyCard destinyCard;

    static ArrayList<Object> actions;

    /**
     * Set up a common {@link DestinyCard} instance with predefined actions for all test methods.
     */
    @BeforeAll
    static void setup(){
        actions = new ArrayList<>(Arrays.asList(0, 114, 0));
        destinyCard = new DestinyCard("It's time for the FINAL!", actions);
    }

    /**
     * Test the {@link DestinyCard#getMessage()} method to ensure it correctly returns the message of the destiny card.
     */
    @Test
    void testGetMessage(){
        assertEquals("It's time for the FINAL!", destinyCard.getMessage());
    }

    /**
     * Test the {@link DestinyCard#getActions()} method to ensure it correctly returns the ArrayList of actions
     * associated with the destiny card.
     */
    @Test
    void testGetActions(){
        assertEquals(actions, destinyCard.getActions());
    }

    /**
     * Test the {@link DestinyCard#toString()} method to ensure it returns the expected string representation
     * of the destiny card containing the message and actions.
     */
    @Test
    void testToString(){
        String expectedString = "DestinyCard{message='It's time for the FINAL!', actions=[money: 0, position: 114, rounds: 0]}";
        assertEquals(expectedString, destinyCard.toString());
    }

}