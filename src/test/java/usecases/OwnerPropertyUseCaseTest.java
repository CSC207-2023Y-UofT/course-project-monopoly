package usecases;

import entities.Player;
import entities.Property;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presenters.OutputPresenter;
import presenters.TerminalInputPresenter;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link OwnerPropertyUseCase}.
 * This class contains test cases to verify the behavior of the {@link OwnerPropertyUseCase} class methods.
 */
class OwnerPropertyUseCaseTest {
    static Player player;
    static Property property;
    static ArrayList<Integer> priceList;
    static ArrayList<Integer> taxList;
    static ByteArrayOutputStream outputStream;
    static PrintStream printStream;
    static InputStream inputStream;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link Property} instance.
     */
    @BeforeEach
    void setup(){
        // Set up the output stream to capture System.out
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        OwnerPropertyUseCase.setInputPresenter(new TerminalInputPresenter());

        // Backup the original standard input stream
        inputStream = System.in;

        // Initialize priceList and taxList with test data.
        priceList = new ArrayList<>(List.of(10, 20, 30, 40, 50, 0));
        taxList = new ArrayList<>(List.of(0, 10, 20, 30, 40, 50));

        // Create a test Property with initial values.
        property = new Property(1, "Test Property", priceList, taxList);

        // Create test Players with initial money.
        player = new Player(0, 1000);

        OutputPresenter.setGameThreadTextArea(new JTextArea());
    }

    /**
     * Reset the environment after each test case.
     * Restores the original standard input and output streams.
     */
    @AfterEach
    void teardown(){
        System.setOut(printStream);
        System.setIn(inputStream);
        OutputPresenter.setGameThreadTextArea(null);
    }

    /**
     * Test case to verify the behavior when the property has reached the maximum upgrade level.
     */
    @Test
    void testOwnerUpgradeMaxLevelReached(){
        // Upgrade the property to the maximum level (5).
        property.upgradeLevel();
        property.upgradeLevel();
        property.upgradeLevel();
        property.upgradeLevel();
        property.upgradeLevel();

        // Call the ownerUpgrade method to test the behavior.
        OwnerPropertyUseCase.ownerUpgrade(player, property);

        // Capture the output messages.
        String expectedMessage = outputStream.toString().trim();

        // Check if the property level remains unchanged and the owner is notified.
        assertNull(property.getOwner());
        assertEquals(1000, player.getMoney()); // Check if money remains unchanged.

        assertTrue(expectedMessage.contains(property.getName() + " has reached maximum level (5). "));
        assertTrue(expectedMessage.contains("Player " + player.getUserId() + " ignored " + property.getName() + "."));
    }

    /**
     * Test case to verify the behavior when the player does nothing.
     */
    @Test
    void testOwnerUpgradeWhenPlayerIgnore(){
        provideInput("N");

        // Call the ownerUpgrade method that reads from System.in
        OwnerPropertyUseCase.ownerUpgrade(player, property);

        String expectedMessage = outputStream.toString().trim();

        // Check if the property level remains unchanged and the owner is notified.
        assertEquals(0, property.getLevel());
        assertNull(property.getOwner());
        assertEquals(1000, player.getMoney());

        assertTrue(expectedMessage.contains("Player " + player.getUserId() + " ignored " + property.getName() + "."));
    }

    /**
     * Test case to verify the behavior when the player doesn't have enough money to buy/upgrade the property.
     */
    @Test
    void testOwnerUpgradeWithInsufficientMoney(){
        player.setMoney(0);

        provideInput("Y");

        OwnerPropertyUseCase.ownerUpgrade(player, property);

        String expectedMessage = outputStream.toString().trim();

        assertEquals(0, property.getLevel());
        assertNull(property.getOwner());
        assertEquals(0, player.getMoney());

        assertTrue(expectedMessage.contains("Player " + player.getUserId() + " ignored " + property.getName() + "."));
        assertTrue(expectedMessage.contains("Insufficient Fund :("));
    }

    /**
     * Test case to verify the behavior when the player wants to buy the property.
     */
    @Test
    void testOwnerUpgradeWhenBuyProperty(){
        provideInput("Y");

        OwnerPropertyUseCase.ownerUpgrade(player, property);

        String expectedMessage = outputStream.toString().trim();

        assertEquals(1, property.getLevel());
        assertEquals(player, property.getOwner());
        assertEquals(990, player.getMoney());
        assertTrue(player.getProperties().contains(property));

        assertTrue(expectedMessage.contains("Player 0 bought Test Property for 10 TBucks."));
        assertTrue(expectedMessage.contains("Test Property is now at level 1."));
    }

    /**
     * Test case to verify the behavior when the player wants to upgrade the property.
     */
    @Test
    void testOwnerUpgradeWhenUpgradeProperty(){
        property.setOwner(player);
        property.upgradeLevel();
        assertEquals(player, property.getOwner());

        provideInput("Y");

        OwnerPropertyUseCase.ownerUpgrade(player, property);

        String expectedMessage = outputStream.toString().trim();

        assertEquals(2, property.getLevel());
        assertEquals(980, player.getMoney());
        assertTrue(expectedMessage.contains("Player 0 upgraded Test Property for 20 TBucks."));
        assertTrue(expectedMessage.contains("Test Property is now at level 2."));
    }

    // Helper method to set the standard input stream to a predefined input string
    private void provideInput(String data) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
}