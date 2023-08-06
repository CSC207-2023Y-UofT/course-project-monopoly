package usecases.interactors;

import controllers.InitController;
import entities.Destiny;
import entities.GameData;
import entities.Player;
import entities.TTCStation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.impactors.PositionImpactor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link TTCInteractor}.
 */
class TTCInteractorTest {
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

    Player player;

    /**
     * Creates a new game data before each test.
     */
    @BeforeEach
    void setup() {
        originalSystemIn = System.in;
        String message = "4\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBytes());
        System.setIn(inputStream);
        data = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);
        System.setIn(originalSystemIn);
    }

    /**
     * Test for all the players to move at all TTC stations, if the move is correct.
     */
    @Test
    void testRegularMove() {
        // Players
        for (int i = 0; i < 4; i++) {
            data.currentPlayerIndex = i;
            data.setCurrentPlayer();
            player = data.currentPlayer;

            // TTC stations
            for (int j = 0; j < data.blocks.size(); j++) {
                if (!(data.blocks.get(j) instanceof TTCStation))
                    continue;

                // Other blocks
                for (int k = 0; k < data.blocks.size(); k++) {
                    // Cannot test for destiny block because it changes position
                    if (data.blocks.get(k) instanceof Destiny)
                        continue;

                    // If not ttc station
                    if (!(data.blocks.get(k) instanceof TTCStation)) {
                        try {
                            String message = 100 + k + "\nN\n";
                            System.setIn(new ByteArrayInputStream(message.getBytes()));
                            PositionImpactor.absoluteMove(data, 100 + j);
                            TTCInteractor interactor = new TTCInteractor();
                            interactor.interact(data.blocks.get(j), data);
                        } catch (NoSuchElementException ignored) {}
                        finally {
                            assertTrue(data.playerAtPosition.get(k).contains(player));
                        }
                    }
                    // If ttc station
                    else {
                        try {
                            String message = 100 + k + "\n100\n";
                            System.setIn((new ByteArrayInputStream(message.getBytes())));
                            PositionImpactor.absoluteMove(data, 100 + j);
                            TTCInteractor interactor = new TTCInteractor();
                            interactor.interact(data.blocks.get(j), data);
                        } catch (NoSuchElementException ignored) {}
                        finally {
                            assertTrue(data.playerAtPosition.get(0).contains(player));
                        }
                    }
                }
            }
        }
    }

    @AfterEach
    void teardown() {
        System.setIn(originalSystemIn);
    }
}