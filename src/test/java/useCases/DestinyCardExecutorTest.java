package useCases;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import useCases.generator.DestinyCardPoolGenerator;
import useCases.impactor.PositionImpactor;
import useCases.impactor.StatusImpactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DestinyCardExecutor}.
 */
class DestinyCardExecutorTest {
    static GameData data;

    static Player player;

    static Destiny destiny;

    static ArrayList<DestinyCard> cardPool;

    /**
     * Sets up the test environment before all test cases are executed.
     * Initializes the game data and player objects with test data.
     */
    @BeforeAll
    static void setup(){
        // Prepare test game data
        ArrayList<Block> blocks = new ArrayList<>();

        StartingPoint startingPoint = new StartingPoint(100, 100);
        blocks.add(startingPoint);

        destiny = new Destiny(101);
        DestinyCardPoolGenerator.generateDestinyCardPool("./data/test/destiny_card_test.csv", destiny);
        blocks.add(destiny);
        cardPool = destiny.getDestinyCardPool();

        ExamCenter examCenter = new ExamCenter(102);
        blocks.add(examCenter);

        player = new Player(0, 1000);
        StatusImpactor.initStatus(player);
        ArrayList<Player> players = new ArrayList<>(List.of(player));

        HashMap<Integer, ArrayList<Player>> position = new HashMap<>();
        for (int i = 0; i < blocks.size(); i++) {
            position.put(i, new ArrayList<>());
        }
        position.get(0).addAll(players);
        for (Player player: players) {
            player.setPosition(blocks.get(0).getId());
        }

        data = new GameData(1, blocks, players, position);
    }

    // Test methods

    /**
     * Test the execution of a destiny card with null game data.
     * The method should handle the null data and return an appropriate message.
     */
    @Test
    void testExecuteCardWithNullData(){
        DestinyCard chosenCard = DestinyCardChooser.chooseCard(destiny);

        assertNotNull(chosenCard);

        String message = DestinyCardExecutor.executeCard(null, player, chosenCard);

        assertEquals("Choose card again", message);
    }

    /**
     * Test the execution of a destiny card with missing action data.
     * The method should handle the invalid action data and return an appropriate message.
     */
    @Test
    void testExecuteCardWithMissingActionData(){
        DestinyCard card = new DestinyCard("It's time for the FINAL!", new ArrayList<>(Arrays.asList(0,0)));

        String message = DestinyCardExecutor.executeCard(data, player, card);

        assertEquals("Choose card again", message);
    }

    /**
     * Test the execution of a destiny card with invalid action data.
     * The method should handle the invalid action data and return an appropriate message.
     */
    @Test
    void testExecuteCardWithInvalidActionData(){
        DestinyCard card = new DestinyCard("It's time for the FINAL!", new ArrayList<>(Arrays.asList(0,0, "N/A")));

        String message = DestinyCardExecutor.executeCard(data, player, card);

        assertEquals("Choose card again", message);
    }

    /**
     * Test the execution of a destiny card that deducts money from the player.
     * The player's money should be reduced by the specified amount in the destiny card.
     */
    @Test
    void testExecuteCardDeductMoney(){
        DestinyCard card = cardPool.get(0);

        DestinyCardExecutor.executeCard(data,player,card);

        assertEquals(700, player.getMoney());
    }

    /**
     * Test the execution of a destiny card that give money to the player.
     * The player's money should be increased by the specified amount in the destiny card.
     */
    @Test
    void testExecuteCardGiveMoney(){
        DestinyCard card = cardPool.get(1);

        DestinyCardExecutor.executeCard(data,player,card);

        assertEquals(1600, player.getMoney());
    }

    /**
     * Test the execution of a destiny card that allows the player advance a specific distance.
     */
    @Test
    void testExecuteCardAdvance(){
        DestinyCard card = cardPool.get(2);

        DestinyCardExecutor.executeCard(data,player,card);

        assertEquals(101, player.getPosition());
    }

    /**
     * Test the execution of a destiny card that allows the player to pass the starting point.
     * The player should be given the bonus when passing through the starting point
     */
    @Test
    void testExecuteCardPassStartingPoint(){
        DestinyCard card = cardPool.get(2);

        DestinyCardExecutor.executeCard(data,player,card);

        assertEquals(1100, player.getMoney());
    }

    /**
     * Test the execution of a destiny card that allows the player move back a specific distance.
     */
    @Test
    void testExecuteCardMoveBack(){
        DestinyCard card = cardPool.get(3);

        DestinyCardExecutor.executeCard(data,player,card);

        assertEquals(101, player.getPosition());
    }

    /**
     * Test the execution of a destiny card that teleports the player to a specific block.
     */
    @Test
    void testExecuteCardTeleportation(){
        DestinyCard card = cardPool.get(4);

        DestinyCardExecutor.executeCard(data,player,card);

        assertEquals(102, player.getPosition());
    }

    /**
     * Test the execution of a destiny card that causes the player to miss a certain number of rounds.
     */
    @Test
    void testExecuteCardMissRounds(){
        DestinyCard card = cardPool.get(5);

        DestinyCardExecutor.executeCard(data, player, card);

        assertEquals(-1, player.getStatus().get("movable"));
    }

    /**
     * Cleans up the test environment after each test case is executed.
     * Resets the player's money and position to initial values.
     */
    @AfterEach
    void teardown(){
        player.setMoney(1000);
        PositionImpactor.absoluteMove(data, 100);
    }
}