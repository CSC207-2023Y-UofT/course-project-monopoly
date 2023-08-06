package usecases.impactors;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.StatusChecker;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StatusImpactor}.
 */
class StatusImpactorTest {
    private static Player player;
    private static HashMap<String, Integer> initStatusMap;

    static final int LOWER_LIMIT = -1000;
    static final int UPPER_LIMIT = 1000;

    /**
     * Set up function for this test.
     * Initializes a player and the initial status map.
     */
    @BeforeEach
    public void setup() {
        player = new Player(1, 1);
        initStatusMap = new HashMap<>();
        initStatusMap.put("playable", 0);
        initStatusMap.put("movable", 0);
        HashMap<String, Integer> newMap = new HashMap<>(initStatusMap);
        player.setStatus(newMap);
    }

    /**
     * Test the {@link StatusImpactor#initStatus(Player player)} method to check if it correctly sets the initial statuses for a player.
     */
    @Test
    public void testInitStatus() {
        StatusImpactor.initStatus(player);
        assertEquals(initStatusMap, player.getStatus());
    }

    /**
     * Test the {@link StatusImpactor#changeStatus(Player, String, Integer)} method to check for correctness of changing the status attributes.
     * It covers normal cases where the statuses are set to various practical values.
     */
    @Test
    public void testChangeStatusNormal() {
        for (int i = LOWER_LIMIT; i < UPPER_LIMIT; i++) {
            StatusImpactor.changeStatus(player, "playable", i);
            StatusImpactor.changeStatus(player, "movable", i);
            assertEquals(i, player.getStatus().get("playable"));
            assertEquals(i, player.getStatus().get("movable"));
        }
    }

    /**
     * Test the {@link StatusImpactor#changeStatus(Player)} method with a single argument.
     * It checks if the method correctly adds 1 to the movable status if it's negative, and does nothing otherwise.
     */
    @Test
    public void testChangeStatusSingle() {
        StatusImpactor.changeStatus(player, "movable", -1000);
        for (int i = 0; i < UPPER_LIMIT; i++) {
            assertFalse(StatusChecker.isMovable(player));
            StatusImpactor.changeStatus(player);
        }
        assertTrue(StatusChecker.isMovable(player));
        for (int i = 0; i < UPPER_LIMIT; i++) {
            StatusImpactor.changeStatus(player);
            assertEquals(0, player.getStatus().get("movable"));
        }
    }
}