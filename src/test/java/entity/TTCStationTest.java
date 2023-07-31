package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link TTCStation}.
 * This class contains test cases to verify the behavior of the {@link TTCStation} class methods.
 */
class TTCStationTest {

    static TTCStation ttcStation;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link TTCStation} instance.
     */
    @BeforeAll
    static void setup(){
        ttcStation = new TTCStation(101);
    }

    /**
     * Test the {@link TTCStation#getBlockName()} method to ensure it correctly returns the name of the TTCStation block.
     */
    @Test
    void testGetBlockName() {
        assertEquals("ttcstation", ttcStation.getBlockName());
    }
}