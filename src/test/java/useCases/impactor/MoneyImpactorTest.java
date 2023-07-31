package useCases.impactor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import useCases.*;
import entity.*;

public class MoneyImpactorTest {
    static Player giver;
    static Player receiver;

    @BeforeAll
    static void setup() {
        giver = new Player(1, 1000);
        receiver = new Player(2, 1000);
        StatusImpactor.initStatus(giver);
        StatusImpactor.initStatus(receiver);
    }

    @Test
    public void testDeductNormal() {
        for (int i = -1000; i < 1000; i++) {
            MoneyImpactor.deduct(i, giver);
            assertEquals(1000-i, giver.getMoney());
            giver.setMoney(1000);
        }
    }

    @Test
    public void testDeductOverflow() {
        for (int i = 1001; i < 2000; i++) {
            MoneyImpactor.deduct(i, giver);
            assertFalse(StatusChecker.isPlayable(giver));
        }
        giver.setMoney(1000);
        StatusImpactor.initStatus(giver);
    }

    @Test
    public void testTradeNormal() {
        for (int i = -1000; i < 1000; i++) {
            MoneyImpactor.trade(i, giver, receiver);
            assertEquals(1000-i, giver.getMoney());
            assertEquals(1000+i, receiver.getMoney());
            giver.setMoney(1000);
            receiver.setMoney(1000);
        }
    }

    @Test
    public void testTradeLeftOverflow() {
        for (int i = 1001; i < 2000; i++) {
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

    @AfterEach
    void teardown() {
        giver.setMoney(1000);
        receiver.setMoney(1000);
        StatusImpactor.changeStatus(giver, "playable", 1);
        StatusImpactor.changeStatus(receiver, "playable", 1);
    }
}