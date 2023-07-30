package useCases.generator;

import entity.Destiny;
import entity.DestinyCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DestinyCardPoolGenerator}.
 */
class DestinyCardPoolGeneratorTest {
    static String testFileName;
    static String invalidFileName;
    static Destiny destiny;


    /**
     * Setup method executed before all test methods.
     */
    @BeforeAll
    static void setup(){
        testFileName = "test_data.csv";
        invalidFileName = "non_existent_file.csv";
        destiny = new Destiny(101);
    }

    /**
     * Test the successful generation of the destiny card pool from a valid test data file.
     */
    @Test
    void testGenerateDestinyCardPoolSuccessfully(){
        // Prepare a test data file with content for testing
        createTestDataFile(testFileName,
                "\"Lost your TCard! Pay 200 TBucks for a replacement.\",-200,0,0\n" +
                        "\"It's time for the FINAL!\",0,114,0");

        // Test the method with the test data file
        DestinyCardPoolGenerator.generateDestinyCardPool(testFileName, destiny);

        // Check if destiny card pool has been populated
        ArrayList<DestinyCard> expectedCardPool = new ArrayList<>(Arrays.asList(
                new DestinyCard("Lost your TCard! Pay 200 TBucks for a replacement.", new ArrayList<>(Arrays.asList(-200, 0, 0))),
                new DestinyCard("It's time for the FINAL!", new ArrayList<>(Arrays.asList(0, 114, 0)))
        ));
        ArrayList<DestinyCard> actualCardPool = destiny.getDestinyCardPool();
        assertNotNull(actualCardPool);
        assertEquals(2, actualCardPool.size());

        for(int i = 0; i < actualCardPool.size(); i++){
            assertEquals(expectedCardPool.get(i).toString(), actualCardPool.get(i).toString());
        }

        // Clean up: delete the test data file
        deleteTestDataFile(testFileName);
    }

    /**
     * Test for generating destiny card pool with a null Destiny instance.
     * The method should throw a NullPointerException in this case.
     */
    @Test
    void testGenerateDestinyCardPoolWithNullDestiny(){
        // Prepare a test data file with content for testing
        createTestDataFile(testFileName,
                "\"It's time for the FINAL!\",0,114,0");

        assertThrows(NullPointerException.class,
                () -> DestinyCardPoolGenerator.generateDestinyCardPool(testFileName, null));

        // Clean up: delete the test data file
        deleteTestDataFile(testFileName);
    }

    /**
     * Test for generating destiny card pool with an invalid (non-existent) file.
     * The method should print an error message to the standard error stream in this case.
     */
    @Test
    void testGenerateDestinyCardPoolInvalidFile(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setErr(printStream);

        DestinyCardPoolGenerator.generateDestinyCardPool(invalidFileName, destiny);

        String errorMessage = outputStream.toString().trim();
        assertTrue(errorMessage.contains("The specified file '" + invalidFileName + "' does not exist or cannot be found."));

        System.setErr(System.err);
    }

    /**
     * Test for generating destiny card pool with missing values in a CSV line.
     * The method should throw an IllegalArgumentException in this case.
     */
    @Test
    void testGenerateDestinyCardPoolMissingValuesInCSV(){
        // Prepare a test data file with invalid action values in a CSV line
        createTestDataFile(testFileName,
                "\"It's time for the FINAL!\",0,114");
        assertThrows(IllegalArgumentException.class,
                () -> DestinyCardPoolGenerator.generateDestinyCardPool(testFileName, destiny));

        // Clean up: delete the test data file
        deleteTestDataFile(testFileName);
    }

    /**
     * Test for generating destiny card pool with invalid action values in a CSV line.
     * The method should throw a NumberFormatException in this case.
     */
    @Test
    void testGenerateDestinyCardPoolInvalidActionValues(){
        // Prepare a test data file with invalid action values in a CSV line
        createTestDataFile(testFileName,
                "\"It's time for the FINAL!\",0,114,N/A");
        assertThrows(NumberFormatException.class,
                () -> DestinyCardPoolGenerator.generateDestinyCardPool(testFileName, destiny));

        // Clean up: delete the test data file
        deleteTestDataFile(testFileName);
    }


    /**
     * Helper method to create a test data file with the given content.
     *
     * @param fileName The name of the test data file to create.
     * @param content The content to write into the test data file.
     */
    private void createTestDataFile(String fileName, String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to delete the test data file with the given name.
     *
     * @param fileName The name of the test data file to delete.
     */
    private void deleteTestDataFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Failed to delete the test data file: " + fileName);
            }
        }
    }
}