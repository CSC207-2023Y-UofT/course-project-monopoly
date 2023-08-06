package usecases.impactors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.*;
import entities.*;

/**
 * Test class for {@link MoneyImpactor}.
 */
public class MoneyImpactorTest {

    static final int LOWER_LIMIT = -1000;
    static final int UPPER_LIMIT = 1000;
    static Player giver;
    static Player receiver;

    /**
     * Set up method for the test.
     * Initializes the giver and receiver players with upper limit money and sets their status to playable.
     */
    @BeforeEach
    public void setup() {
        giver = new Player(1, UPPER_LIMIT);
        receiver = new Player(2, UPPER_LIMIT);
        StatusImpactor.initStatus(giver);
        StatusImpactor.initStatus(receiver);
        giver.setMoney(UPPER_LIMIT);
        receiver.setMoney(UPPER_LIMIT);
        StatusImpactor.changeStatus(giver, "playable", 1);
        StatusImpactor.changeStatus(receiver, "playable", 1);
    }


    /**
     * Tests the {@link MoneyImpactor#deduct(int, Player)}} method for various practical money values.
     * It verifies that the method correctly deducts the specified amount from the player's money.
     */
    @Test
    public void testDeductNormal() {
        for (int i = LOWER_LIMIT; i < UPPER_LIMIT; i++) {
            MoneyImpactor.deduct(i, giver);
            assertEquals(1000-i, giver.getMoney());
            giver.setMoney(1000);
        }
    }

    /**
     * Tests the {@link MoneyImpactor#deduct(int, Player)}} method when the player doesn't have enough money to deduct.
     * It verifies that the player's status is updated correctly when they do not have sufficient funds.
     */
    @Test
    public void testDeductOverflow() {
        for (int i = UPPER_LIMIT + 1; i < UPPER_LIMIT + UPPER_LIMIT; i++) {
            MoneyImpactor.deduct(i, giver);
            assertFalse(StatusChecker.isPlayable(giver));
        }
        giver.setMoney(1000);
        StatusImpactor.initStatus(giver);
    }


    /**
     * Tests the {@link MoneyImpactor#trade(int, Player, Player)} method for various practical money values.
     * It verifies that the method correctly transfers money between the giver and receiver players.
     */
    @Test
    public void testTradeNormal() {
        for (int i = LOWER_LIMIT; i < UPPER_LIMIT; i++) {
            MoneyImpactor.trade(i, giver, receiver);
            assertEquals(1000-i, giver.getMoney());
            assertEquals(1000+i, receiver.getMoney());
            giver.setMoney(1000);
            receiver.setMoney(1000);
        }
    }


    /**
     * Tests the {@link MoneyImpactor#trade(int, Player, Player)} method when the giver doesn't have enough money to trade.
     * It verifies that the giver's status is updated correctly when they do not have sufficient funds.
     */
    @Test
    public void testTradeLeftOverflow() {
        for (int i = UPPER_LIMIT + 1; i < 2 * UPPER_LIMIT; i++) {
            MoneyImpactor.trade(i, giver, receiver);
            assertEquals(1000-i, giver.getMoney());
            assertFalse(StatusChecker.isPlayable(giver));
            assertEquals(1000+i, receiver.getMoney());
            assertTrue(StatusChecker.isPlayable(receiver));
            giver.setMoney(1000);
            receiver.setMoney(1000);
            StatusImpactor.changeStatus(giver, "playable", 1);
            StatusImpactor.changeStatus(receiver, "playable", 1);
        }
    }

}