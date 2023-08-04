package useCases;

import entity.Player;
import entity.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PasserbyUseCase}.
 * This class contains test cases to verify the behavior of the {@link PasserbyUseCase} class methods.
 */
class PasserbyUseCaseTest {

    static Player passer;
    static Player owner;
    static Property property;
    static ArrayList<Integer> priceList;
    static ArrayList<Integer> taxList;

    @BeforeEach
    void setup(){
        priceList = new ArrayList<>(List.of(10, 20, 30, 40, 50, 0));
        taxList = new ArrayList<>(List.of(0, 10, 20, 30, 40, 50));

        property = new Property(1, "Test Property", priceList, taxList);

        passer = new Player(0, 1000);
        owner = new Player(1, 1000);

        property.setOwner(owner);
    }

    /**
     * Test case to verify the behavior of passerby arrival and the money exchange.
     */
    @Test
    void testPasserByArrival(){
        property.upgradeLevel();

        PasserbyUseCase.passerbyArrival(passer, property, owner);

        assertEquals(990, passer.getMoney());
        assertEquals(1010, owner.getMoney());
    }
}