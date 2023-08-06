package useCases;

import entity.Player;
import entity.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link OwnerIdentifier}.
 */
class OwnerIdentifierTest {

    static Property property;
    static Player player;
    static ArrayList<Integer> priceList;
    static ArrayList<Integer> taxList;

    @BeforeEach
    void setup(){
        priceList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 0));
        taxList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5));

        property = new Property(1, "Test Property", priceList, taxList);

        player = new Player(0, 1000);
    }

    /**
     * Test case to verify that a player is identified as the owner when the property has no owner.
     */
    @Test
    void testIsOwnerWhenPropertyHasNoOwner(){
        assertTrue(OwnerIdentifier.isOwner(player, property));
    }

    /**
     * Test case to verify that a player is identified as the owner when the player is the actual owner of the property.
     */
    @Test
    void testIsOwnerWhenPlayerIsOwner(){
        property.setOwner(player);
        assertTrue(OwnerIdentifier.isOwner(player, property));
    }

    /**
     * Test case to verify that a player is not identified as the owner when the player is not the owner of the property.
     */
    @Test
    void testIsOwnerWhenPlayerIsNotOwner(){
        property.setOwner(new Player(1, 1000));
        assertFalse(OwnerIdentifier.isOwner(player, property));
    }
}