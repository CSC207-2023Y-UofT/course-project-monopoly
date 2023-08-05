package usecases;

import entities.Player;
import entities.StartingPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StartingPointUseCase}.
 * This class contains test cases to verify the behavior of the {@link StartingPointUseCase} class methods.
 */
class StartingPointUseCaseTest {

    static Player player;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link Player} instance and sets the bonus of the StartingPoint.
     */
    @BeforeAll
    static void setup(){
        player = new Player(0, 100);
        StartingPoint.setBonus(1000);
    }

    /**
     * Test case to verify the correctness of the {@link StartingPointUseCase#giveBonus(Player)} method.
     * It checks whether the method correctly adds the starting bonus amount to the player's money.
     */
    @Test
    void testGiveBonus(){
        StartingPointUseCase.giveBonus(player);
        assertEquals(1100, player.getMoney());
    }

}