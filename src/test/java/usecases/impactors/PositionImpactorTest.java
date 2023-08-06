package usecases.impactors;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import entities.GameData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PropertyImpactor}.
 */
class PositionImpactorTest {
    Block[] blocks;
    Player[] players;

    GameData data;

    /**
     * Sets up the test environment by initializing the game data with blocks and players.
     */
    @BeforeEach
    void setUp() {
        blocks = new Block[] {
                new ExamCenter(100),
                new ExamCenter(101),
                new ExamCenter(102),
                new ExamCenter(103),
                new ExamCenter(104),
                new ExamCenter(105),
                new ExamCenter(106),
                new ExamCenter(107),};

        players = new Player[] {
                new Player(1000, 1000),
                new Player(1001, 1000),
                new Player(1002, 1000),
                new Player(1003, 1000),
        };

        HashMap<Integer, ArrayList<Player>> position = new HashMap<>();
        for (int i = 0; i < blocks.length; i++)
            position.put(i, new ArrayList<>());
        position.get(0).addAll(Arrays.asList(players));
        for (Player player: players)
            player.setPosition(blocks[0].getId());

        data = new GameData(4, new ArrayList<>(Arrays.asList(blocks)), new ArrayList<>(Arrays.asList(players)), position);
    }

    /**
     * Test the {@link PositionImpactor#relativeMove(GameData, int)} method.
     * It verifies that the player's position is correctly updated when moving relative to the current position.
     */
    @Test
    void testRelativeMove() {
        for (int m = -1000; m <= 1000; m += 1000) {
            for (int i = 0; i < 4; i++) {
                data.currentPlayerIndex = i;
                data.setCurrentPlayer();
                PositionImpactor.relativeMove(data, 0);
                assertEquals(data.playerAtPosition.get(0).size(), 4);
                for (int j = 1; j < 8; j++) {
                    PositionImpactor.relativeMove(data, m + 1);
                    assertTrue(data.playerAtPosition.get(data.getPositionFromId(100 + j)).contains(players[i]));
                }
                assertEquals(data.playerAtPosition.get(0).size(), 3);
                PositionImpactor.absoluteMove(data, 100);
            }
        }

    }

    /**
     * Test the {@link PositionImpactor#absoluteMove(GameData, int)} method.
     * It verifies that the player's position is correctly updated when moving to an absolute position.
     * It also tests the handling of an invalid block ID.
     */
    @Test
    void testAbsoluteMove() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                data.currentPlayerIndex = i;
                data.setCurrentPlayer();
                PositionImpactor.absoluteMove(data, 100 + j);
                assertTrue(data.playerAtPosition.get(data.getPositionFromId(100 + j)).contains(players[i]));
            }
            assertEquals(data.playerAtPosition.get(data.getPositionFromId(100)).size(), 3-i);
        }
        try {
            PositionImpactor.absoluteMove(data, 1000000);
            fail();
        } catch (RuntimeException ignored) {}
    }
}