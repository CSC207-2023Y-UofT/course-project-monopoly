package usecase.impactor;

import entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionImpactorTest {

    Block[] blocks;
    Player[] players;

    @BeforeEach
    void setUp() {
        blocks = new Block[] {
                new Block(100),
                new Block(101),
                new Block(102),
                new Block(103),
                new Block(104),
                new Block(105),
                new Block(106),
                new Block(107),};

        players = new Player[] {
                new Player(1000, 1000),
                new Player(1001, 1000),
                new Player(1002, 1000),
                new Player(1003, 1000),
        };
    }

    @Test
    void constructor() {
        PositionImpactor impactor = new PositionImpactor(blocks, players);
        for (Player player: players) {
            assertTrue(impactor.getPlayersOnBlock(blocks[0]).contains(player));
        }
        for (Block block: blocks) {
            assertNotNull(impactor.getPlayersOnBlock(block.getId()));
        }
    }

    @Test
    void relativeMove() {
        PositionImpactor impactor = new PositionImpactor(blocks, players);
        for (int m = -1000; m <= 1000; m += 1000) {
            for (int i = 0; i < 4; i++) {
                impactor.relativeMove(players[i], 0);
                assertEquals(impactor.getPlayersOnBlock(blocks[0]).size(), 4);
                for (int j = 1; j < 8; j++) {
                    impactor.relativeMove(players[i], m + 1);
                    assertTrue(impactor.getPlayersOnBlock(100 + j).contains(players[i]));
                }
                assertEquals(impactor.getPlayersOnBlock(100).size(), 3);
                impactor.absoluteMove(players[i], 100);
            }
        }

    }

    @Test
    void absoluteMove() {
        PositionImpactor impactor = new PositionImpactor(blocks, players);
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                impactor.absoluteMove(players[i], 100 + j);
                assertTrue(impactor.getPlayersOnBlock(100 + j).contains(players[i]));
            }
            assertEquals(impactor.getPlayersOnBlock(100).size(), 3-i);
        }
        try {
            impactor.absoluteMove(players[0], 1000000);
            fail();
        } catch (RuntimeException ignored) {
            ;
        }
    }
}