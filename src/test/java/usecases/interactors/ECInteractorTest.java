package usecases.interactors;

import controllers.InitController;
import entities.Block;
import entities.GameData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presenters.PlayerInfoPanel;
import usecases.impactors.PositionImpactor;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ECInteractorTest class contains test cases for the ECInteractor class.
 */
class ECInteractorTest {

    public static final int PLAYER_NUM = 4;
    public static final String propertiesFile = "data/test/properties_test.csv";
    public static final String extraBlocksFile = "data/test/extra_blocks_test.csv";
    public static final String[] destinyFiles = {"data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",
            "data/test/destiny_card_test.csv",};
    static GameData data;

    static InputStream originalSystemIn;

    @BeforeAll
    static void setup() {
        originalSystemIn = System.in;
        String message = PLAYER_NUM + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBytes());
        System.setIn(inputStream);
        data = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);
        System.setIn(originalSystemIn);
    }

    @BeforeEach
    void beforeEach() {
        data.currentPlayerIndex = 0;
        data.setCurrentPlayer();
        PlayerInfoPanel.setallplayerTextAreas(new ArrayList<>(Arrays.asList(
                new JTextArea(),
                new JTextArea(),
                new JTextArea(),
                new JTextArea()
        )));
    }

    /**
     * Test the {@link ECInteractor#interact(Block, GameData)} method to check if it correctly impacts the movable status of the player
     * when the player steps on the exam center block.
     */
    @Test
    void testInteract() {
        for (int i = 0; i < data.playerNum; i++) {
            data.currentPlayerIndex = i;
            data.setCurrentPlayer();
            PositionImpactor.absoluteMove(data, 110);
            ECInteractor interactor = new ECInteractor();
            interactor.interact(data.blocks.get(7), data);
            assertEquals(-2, data.currentPlayer.getStatus().get("movable"));
        }
    }
}