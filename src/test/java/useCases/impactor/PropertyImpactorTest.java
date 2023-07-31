package useCases.impactor;

import entity.Player;
import entity.Property;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PropertyImpactorTest {

    private static Property property;
    private static Player player1;
    private static Player player2;

    /**
     * setup method for this test
     */
    @BeforeAll
    public static void setUp() {
        property = new Property(1,
                "1",
                new ArrayList<>(Arrays.asList(1, 2, 3, 0)),
                new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        player1 = new Player(1,1);
        player2 = new Player(2, 2);
    }

    /**
     * test change owner for three situations: null -> owner, owner -> owner, owner -> null
     */
    @Test
    public void changeOwner() {
        assertNull(property.getOwner());
        PropertyImpactor.changeOwner(player1, property);
        assertEquals(player1, property.getOwner());
        PropertyImpactor.changeOwner(player2, property);
        assertEquals(player2, property.getOwner());
        PropertyImpactor.changeOwner(null, property);
        assertNull(property.getOwner());
    }

    @Test
    public void downgradeToZero() {
        PropertyImpactor.changeOwner(player1, property);
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                property.upgradeLevel();
            }
            property.downgradeToZero();
            assertEquals(0, property.getLevel());
            assertEquals(player1, property.getOwner());
            property.setOwner(player1);
        }
    }
}