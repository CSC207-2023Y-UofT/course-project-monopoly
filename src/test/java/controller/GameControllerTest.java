package controller;

import entity.GameData;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class GameControllerTest {
    static GameController gc;
    static GameData gd;

    @BeforeEach
    void setup(){
        String propertiesFile = "data/test/properties_test.csv";
        String extraBlocksFile = "data/test/extra_blocks_test.csv";
        String[] destinyFiles = {"data/test/destiny_card_chest_test.csv",
                "data/test/destiny_card_chance_test.csv",
                "data/test/destiny_card_chest_test.csv",
                "data/test/destiny_card_chance_test.csv",
                "data/test/destiny_card_chest_test.csv",
                "data/test/destiny_card_chance_test.csv"};

        // Simulate input to the standard IO
        final int playerNumber = 4;

        String input = String.valueOf(playerNumber);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        gd = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);

        gc = new GameController(gd);
    }

    @Test
    public void testIsGameOver() {
        assertFalse(gc.isGameOver());
    }

    @Test
    public void testGetMaxMoneyPlayer() {
        // It should choose the last one
        assertEquals(gc.getMaxMoneyPlayer().getUserId(), 3);
    }

    @Test
    public void testUpdatePlayablePlayer() {
        // It should remove one player count
        HashMap<String, Integer> notPlayableStatus = new HashMap<>();
        notPlayableStatus.put("playable", -1);
        gd.currentPlayer.setStatus(notPlayableStatus);

        gc.updatePlayablePlayer();

        assertEquals(gd.playerNum, 3);
    }

    @Test
    public void testIsCurrentMovable() {
        assertTrue(gc.isCurrentMovable());
    }

    @Test
    public void testSettleOneRound() {
        gc.settleOneRound();
        assertEquals(gd.gameRounds, 1);
    }

    @Test
    public void testRandomDice() {
        assertTrue(1 <= gc.randomDice() && gc.randomDice() <= 6);
    }

    @Test
    public void testFinish() {
        // Setup output stream for testing
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        gc.finish();

        // In this setup, everyone is the winner
        assertEquals("Game over! winner is Player 0 and Player 1 and Player 2 and Player 3!",
                outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }
}
