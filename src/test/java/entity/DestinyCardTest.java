package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DestinyCardTest {

    static DestinyCard destinyCard;

    static ArrayList<Object> actions;

    @BeforeAll
    static void setup(){
        actions = new ArrayList<>(Arrays.asList(0, 114, 0));
        destinyCard = new DestinyCard("It's time for the FINAL!", actions);
    }

    @Test
    void testGetMessage(){
        assertEquals("It's time for the FINAL!", destinyCard.getMessage());
    }

    @Test
    void testGetActions(){
        assertEquals(actions, destinyCard.getActions());
    }

    @Test
    void testToString(){
        String expectedString = "DestinyCard{message='It's time for the FINAL!', actions=[money: 0, position: 114, rounds: 0]}";
        assertEquals(expectedString, destinyCard.toString());
    }

}