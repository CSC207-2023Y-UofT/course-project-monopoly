package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DestinyTest {

    Destiny destiny;

    ArrayList<DestinyCard> destinyCardList;

    ArrayList<DestinyCard> destinyCardListToAdd;

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

    @Test
    void testGetBlockName() {
        assertEquals("destiny", destiny.getBlockName());
    }

    @Test
    void testAddDestinyCardPool() {
        destiny.addDestinyCardPool(destinyCardList);
        assertEquals(destinyCardList, destiny.getDestinyCardPool());

        destiny.addDestinyCardPool(destinyCardListToAdd);
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardList));
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardListToAdd));
    }

    @Test
    void testGetDestinyCardPool() {
        assertTrue(destiny.getDestinyCardPool().isEmpty());

        destiny.addDestinyCardPool(destinyCardList);
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardList));

        destiny.addDestinyCardPool(destinyCardListToAdd);
        assertTrue(destiny.getDestinyCardPool().containsAll(destinyCardListToAdd));
    }
}
