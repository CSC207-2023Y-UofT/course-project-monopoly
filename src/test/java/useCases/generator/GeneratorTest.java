package useCases.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public interface GeneratorTest {

    /**
     * Helper method to create a test data file with the given content.
     *
     * @param fileName The name of the test data file to create.
     * @param content The content to write into the test data file.
     */
    static void createTestDataFile(String fileName, String content) {
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
    static void deleteTestDataFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Failed to delete the test data file: " + fileName);
            }
        }
    }
}
