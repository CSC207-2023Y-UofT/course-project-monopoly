package usecases.impactors;

import entities.Player;
import entities.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class for {@link PropertyImpactor}
 */
public class PropertyImpactorTest {

    private static Property property;
    private static Player previousOwner;
    private static Player newOwner;

    /**
     * Setup method for this test. Initializes a property and two players.
     */
    @BeforeEach
    public void setUp() {
        property = new Property(1,
                "1",
                new ArrayList<>(Arrays.asList(1, 2, 3, 0)),
                new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        previousOwner = new Player(1, 1);
        newOwner = new Player(2, 2);
    }

    /**
     * Test the {@link PropertyImpactor#changeOwner(Player, Property)}} method with three situations: null -> owner, previousOwner -> newOwner, owner -> null.
     * Verifies that the property's owner is correctly changed or set to null.
     */
    @Test
    public void testChangeOwner() {
        assertNull(property.getOwner());

        PropertyImpactor.changeOwner(previousOwner, property);
        assertEquals(previousOwner, property.getOwner());

        PropertyImpactor.changeOwner(newOwner, property);
        assertEquals(newOwner, property.getOwner());

        PropertyImpactor.changeOwner(null, property);
        assertNull(property.getOwner());
    }

    /**
     * Test the {@link PropertyImpactor#downgradeToZero(Property)} method.
     * Verifies that the property's level is correctly downgraded to zero while keeping the owner intact.
     */
    @Test
    public void testDowngradeToZero() {
        PropertyImpactor.changeOwner(previousOwner, property);
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                property.upgradeLevel();
            }
            property.downgradeToZero();
            assertEquals(0, property.getLevel());
            assertEquals(previousOwner, property.getOwner());
            property.setOwner(previousOwner);
        }
    }
}