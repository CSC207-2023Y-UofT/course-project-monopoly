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

    @BeforeAll
    public static void setup() {
        player = new Player(1, 1);
        initStatusMap = new HashMap<>();
        initStatusMap.put("playable", 0);
        initStatusMap.put("movable", 0);
    }

    @Test
    public void initStatus() {
        StatusImpactor.initStatus(player);
        assertEquals(initStatusMap, player.getStatus());
    }

    @Test
    public void changeStatusNormal() {
        for (int i = -1000; i < 1000; i++) {
            StatusImpactor.changeStatus(player, "playable", i);
            StatusImpactor.changeStatus(player, "movable", i);
            assertEquals(i, player.getStatus().get("playable"));
            assertEquals(i, player.getStatus().get("movable"));
        }
    }

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