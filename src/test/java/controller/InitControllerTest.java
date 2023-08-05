package controller;

import entity.GameData;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link InitController}.
 * This class contains test cases to verify the behavior of the {@link InitController} class methods.
 */
public class InitControllerTest {
    /**
     * Test the {@link InitController#init(String, String, String[])}} method to ensure
     * it correctly returns the GameData.
     */
    @Test
    public void testInit(){
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

        GameData gd = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);

        // Check player
        assertEquals(gd.playerNum, 4);
        assertEquals(gd.currentPlayerIndex, 0);

        // Checking blocks size
        assertEquals(gd.blocks.size(), 40);
        assertEquals(gd.currentPlayers.size(), 4);

        // Checking rounds
        assertEquals(gd.gameRounds, 0);
    }
}
