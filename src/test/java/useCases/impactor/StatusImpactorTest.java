package useCases.impactor;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import useCases.StatusChecker;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StatusImpactorTest {
    private static Player player;
    private static HashMap<String, Integer> initStatusMap;

    /**
     * setup function for this test
     */
    @BeforeAll
    public static void setup() {
        player = new Player(1, 1);
        initStatusMap = new HashMap<>();
        initStatusMap.put("playable", 0);
        initStatusMap.put("movable", 0);
    }

    @Test
    /**
     * check if init sets the right statuses
     */
    public void initStatus() {
        StatusImpactor.initStatus(player);
        assertEquals(initStatusMap, player.getStatus());
    }

    /**
     * check for correctness of change status
     */
    @Test
    public void changeStatusNormal() {
        for (int i = -1000; i < 1000; i++) {
            StatusImpactor.changeStatus(player, "playable", i);
            StatusImpactor.changeStatus(player, "movable", i);
            assertEquals(i, player.getStatus().get("playable"));
            assertEquals(i, player.getStatus().get("movable"));
        }
    }

    /**
     * check for correctness for change status for single argument
     * check if it correctly adds 1 to movable status if it's negative,
     * and does nothing otherwise
     */
    @Test
    public void changeStatusSingle() {
        StatusImpactor.changeStatus(player, "movable", -1000);
        for (int i = 0; i < 1000; i++) {
            assertFalse(StatusChecker.isMovable(player));
            StatusImpactor.changeStatus(player);
        }
        assertTrue(StatusChecker.isMovable(player));
        for (int i = 0; i < 1000; i++) {
            StatusImpactor.changeStatus(player);
            assertEquals(0, player.getStatus().get("movable"));
        }
    }

    @AfterEach
    public void teardown() {
        HashMap<String, Integer> newMap = new HashMap<>(initStatusMap);
        player.setStatus(newMap);
    }
}