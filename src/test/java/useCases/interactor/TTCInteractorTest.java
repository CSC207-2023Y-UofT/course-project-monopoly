package useCases.interactor;

import controller.InitController;
import entity.Destiny;
import entity.GameData;
import entity.Player;
import entity.TTCStation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import useCases.impactor.PositionImpactor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

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
     * creates a new gamedata
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
     * test for all the players to move at all ttc station, if the move is correct
     */
    @Test
    void testRegularMove() {
        //player
        for (int i = 0; i < 4; i++) {
            data.currentPlayerIndex = i;
            data.setCurrentPlayer();
            player = data.currentPlayer;
            // ttc station
            for (int j = 0; j < data.blocks.size(); j++) {
                if (!(data.blocks.get(j) instanceof TTCStation))
                    continue;
                // other blocks
                for (int k = 0; k < data.blocks.size(); k++) {
                    // cannot test for destiny block because it changes position
                    if (data.blocks.get(k) instanceof Destiny)
                        continue;
                    // is not ttc station
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
                    // is ttc station
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