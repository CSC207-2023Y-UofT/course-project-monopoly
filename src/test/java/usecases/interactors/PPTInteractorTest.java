package usecases.interactors;

import controllers.InitController;

import entities.Block;
import entities.GameData;

import entities.Player;
import entities.Property;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presenters.OutputPresenter;
import usecases.impactors.PositionImpactor;


import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PPTInteractor}.
 */
class PPTInteractorTest {
//    public static final String propertiesFile = "data/test/properties_test.csv";
//    public static final String extraBlocksFile = "data/test/extra_blocks_test.csv";
//    public static final String[] destinyFiles = {"data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",
//            "data/test/destiny_card_test.csv",};
//    static GameData data;
//
//    static InputStream originalSystemIn;
//
//    /**
//     * Creates a new game data before each test.
//     */
//    @BeforeEach
//    void setup() {
//        originalSystemIn = System.in;
//        String message = "4\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBytes());
//        System.setIn(inputStream);
//        data = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);
//        System.setIn(originalSystemIn);
//        for (Player player: data.currentPlayers)
//            player.setMoney(1000000000);
//        data.currentPlayerIndex = 0;
//        data.setCurrentPlayer();
//        OutputPresenter.setGameThreadTextArea(new JTextArea());
//    }
//
//    /**
//     * Helper function to resets all the property levels to 0 and owners to null.
//     *
//     * @param data The game data to reset.
//     */
//    private static void resetProperty(GameData data) {
//        for (Block block: data.blocks) {
//            if (block instanceof Property) {
//                ((Property) block).downgradeToZero();
//                ((Property) block).setOwner(null);
//            }
//        }
//    }
//
//    /**
//     * Test the interaction of players buying and upgrading properties for all levels.
//     */
//    @Test
//    void testBuyProperty() {
//        for (int k = 0; k < 4; k++) {
//            data.currentPlayerIndex = k;
//            data.setCurrentPlayer();
//            resetProperty(data);
//            for (int i = 0; i < data.blocks.size(); i++) {
//                if (! (data.blocks.get(i) instanceof Property))
//                    continue;
//                for (int j = 0; j < 3; j++) {
//                    int expected = data.currentPlayer.getMoney() - ((Property) data.blocks.get(i)).getPrice();
//                    String message = "y\n";
//                    System.setIn(new ByteArrayInputStream(message.getBytes()));
//                    PositionImpactor.absoluteMove(data, 100+i);
//                    PPTInteractor interactor = new PPTInteractor();
//                    interactor.interact(data.blocks.get(i), data);
//                    assertEquals(expected, data.currentPlayer.getMoney());
//                    assertEquals(((Property) data.blocks.get(i)).getOwner(), data.currentPlayer);
//                }
//            }
//        }
//    }
//
//    /**
//     * Test the interaction of players paying tax for properties with different owners at different levels.
//     */
//    @Test
//    void testPayTax() {
//        for (int i = 0; i < 4; i++) {
//
//            // block
//            for (int j = 0; j < data.blocks.size(); j++) {
//                if (! (data.blocks.get(j) instanceof Property))
//                    continue;
//
//                // levels
//                for (int k = 0; k < 3; k++) {
//                    // owner
//                    data.currentPlayerIndex = i;
//                    data.setCurrentPlayer();
//                    Player owner = data.currentPlayer;
//                    String message = "y\n";
//                    System.setIn(new ByteArrayInputStream(message.getBytes()));
//                    PositionImpactor.absoluteMove(data, 100+j);
//                    PPTInteractor interactor = new PPTInteractor();
//                    interactor.interact(data.blocks.get(j), data);
//
//                    //passer
//                    for (int l = 0; l < data.playerNum; l++) {
//                        if (l == i)
//                            continue;
//                        data.currentPlayerIndex = l;
//                        data.setCurrentPlayer();
//                        int expected = data.currentPlayer.getMoney() - ((Property) data.blocks.get(j)).getTax();
//                        int expected2 = owner.getMoney() + ((Property) data.blocks.get(j)).getTax();
//                        PositionImpactor.absoluteMove(data, 100+j);
//                        PPTInteractor interactor1 = new PPTInteractor();
//                        interactor1.interact(data.blocks.get(j), data);
//                        assertEquals(expected, data.currentPlayer.getMoney());
//                        assertEquals(expected2, owner.getMoney());
//                    }
//                }
//            }
//            resetProperty(data);
//        }
//    }
//
//    @AfterEach
//    void teardown() {
//        System.setIn(originalSystemIn);
//        OutputPresenter.setGameThreadTextArea(null);
//    }

}