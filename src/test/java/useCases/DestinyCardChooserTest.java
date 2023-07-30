package useCases;

import entity.Destiny;
import entity.DestinyCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DestinyCardChooserTest {

    static Destiny destiny;

    @BeforeAll
    static void setup(){
        destiny = new Destiny(101);
    }

    @Test
    void testChooseCardFromNonEmptyCardPool(){
        ArrayList<DestinyCard> cardPool = new ArrayList<>(Arrays.asList(
                new DestinyCard("Met a snowstorm and can not leave. Miss a round.", new ArrayList<>(Arrays.asList(0, 0, -1))),
                new DestinyCard("It's time for the FINAL!", new ArrayList<>(Arrays.asList(0, 114, 0)))
        ));
        destiny.addDestinyCardPool(cardPool);

        DestinyCard chosenCard = DestinyCardChooser.chooseCard(destiny);

        assertNotNull(chosenCard);
        assertTrue(cardPool.contains(chosenCard));
    }
    @Test
    void testChooseCardFromEmptyCardPool(){
        DestinyCard chosenCard = DestinyCardChooser.chooseCard(destiny);
        assertNull(chosenCard);
    }


}