package useCases.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PropertyGenerator}.
 */
class PropertyGeneratorTest implements GeneratorTest{
    static String testFileName;
    static String invalidFileName;

    @BeforeAll
    static void setup(){
        testFileName = "test_data.csv";
        invalidFileName = "non_existent_file.csv";
    }

    /**
     * Test for generating properties with an invalid (non-existent) file.
     * The method should print an error message to the standard error stream in this case.
     */
    @Test
    void testGeneratePropertiesInvalidFile(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setErr(printStream);

        PropertyGenerator.generateProperties(invalidFileName);

        String errorMessage = outputStream.toString().trim();
        assertTrue(errorMessage.contains("The specified file '" + invalidFileName + "' does not exist or cannot be found."));

        System.setErr(System.err);
    }

    /**
     * Test for generating properties with missing values in a CSV line.
     * The method should throw an IllegalArgumentException in this case.
     */
    @Test
    void testGeneratePropertiesMissingValuesInCSV(){
        // Prepare a test data file with invalid action values in a CSV line
        GeneratorTest.createTestDataFile(testFileName,
                "\"Mediterranean Avenue\",60,50,50,45,45,4,10,30,40,70");
        assertThrows(IllegalArgumentException.class,
                () -> PropertyGenerator.generateProperties(testFileName));

        // Clean up: delete the test data file
        GeneratorTest.deleteTestDataFile(testFileName);
    }

    /**
     * Test for generating properties with invalid action values in a CSV line.
     * The method should throw a NumberFormatException in this case.
     */
    @Test
    void testGeneratePropertiesInvalidValuesInCSV(){
        // Prepare a test data file with invalid action values in a CSV line
        GeneratorTest.createTestDataFile(testFileName,
                "110,\"Mediterranean Avenue\",60,50,50,45,45,4,10,30,40,N/A");
        assertThrows(IllegalArgumentException.class,
                () -> PropertyGenerator.generateProperties(testFileName));

        // Clean up: delete the test data file
        GeneratorTest.deleteTestDataFile(testFileName);
    }
}