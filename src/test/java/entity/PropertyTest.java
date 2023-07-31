package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Property}.
 * This class contains test cases to verify the behavior of the {@link Property} class methods.
 */
class PropertyTest {
    static Property p;
    static ArrayList<Integer> priceList;
    static ArrayList<Integer> taxList;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link Property} instance.
     */
    @BeforeEach
    void setup(){
        priceList = new ArrayList<>(List.of(1, 2, 3, 0));
        taxList = new ArrayList<>(List.of(0, 1, 2, 3));

        p = new Property(1, "Test Property", priceList, taxList);
    }

    /**
     * Test the {@link Property#getName()} to ensure that it returns the right name
     */
    @Test
    void testGetName() {
        assertEquals("Test Property", p.getName());
    }

    /**
     * Test the {@link Property#getBlockName()} to ensure that it returns the right name
     */
    @Test
    void testGetBlockName() {
        assertEquals("property", p.getBlockName());
    }

    /**
     * Test the {@link Property#setName(String)} to ensure that it returns the right name
     */
    @Test
    void testSetBlockName() {
        p.setName("Modified Name");
        assertEquals("Modified Name", p.getName());
    }

    /**
     * Test the {@link Property#getLevel()} to ensure that the default is 0
     */
    @Test
    void testGetLevel() {
        assertEquals(0, p.getLevel());
    }

    /**
     * Test the {@link Property#upgradeLevel()} to ensure that the level did upgrade
     * Requires getLevel to pass
     * Also requires getPrice and getTax to pass
     */
    @Test
    void testUpgradeLevel() {
        assertEquals(0, p.getLevel());
        p.upgradeLevel();
        assertEquals(1, p.getLevel());
        assertEquals(p.getPrice(), 2);
        assertEquals(p.getTax(), 1);
    }

    /**
     * Test the {@link Property#downgradeToZero()} to ensure that the level did downgrade to 0
     * Requires getLevel to pass
     */
    @Test
    void testDowngradeToZero() {
        assertEquals(0, p.getLevel());
        p.upgradeLevel();
        p.upgradeLevel();
        assertEquals(2, p.getLevel());
        p.downgradeToZero();
        assertEquals(0, p.getLevel());
    }

    /**
     * Test the {@link Property#getOwner()} to ensure that the owner is null when no one owns it
     */
    @Test
    void testGetOwner() {
        assertNull(p.getOwner());
    }

    /**
     * Test the {@link Property#setOwner(Player)} to ensure that owner is set correctly
     */
    @Test
    void testSetOwner() {
        Player player = new Player(1, 1000);
        p.setOwner(player);

        assertEquals(p.getOwner(), player);
    }

    /**
     * Test the {@link Property#getPrice()} to ensure that the price is right
     */
    @Test
    void testGetPrice() {
        assertEquals(p.getPrice(), 1);
    }

    /**
     * Test the {@link Property#getTax()} to ensure that tax is right
     */
    @Test
    void testGetTax() {
        assertEquals(p.getTax(), 0);
    }

    /**
     * Test the {@link Property#getPriceList()}
     */
    @Test
    void testGetPriceList() {
        assertEquals(p.getPriceList(), priceList);
    }

    /**
     * Test the {@link Property#setPriceList(ArrayList)}
     */
    @Test
    void testSetPriceList() {
        ArrayList<Integer> newPriceList = new ArrayList<>(List.of(4, 5, 6));
        p.setPriceList(newPriceList);
        assertEquals(p.getPriceList(), newPriceList);
    }

    /**
     * Test the {@link Property#getTaxList()}
     */
    @Test
    void testGetTaxList() {
        assertEquals(p.getTaxList(), taxList);
    }

    /**
     * Test the {@link Property#setTaxList(ArrayList)}
     */
    @Test
    void testSetTaxList() {
        ArrayList<Integer> newTaxList = new ArrayList<>(List.of(4, 5, 6));
        p.setPriceList(newTaxList);
        assertEquals(p.getPriceList(), newTaxList);
    }

}
