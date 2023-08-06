package datagateways;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The DestinyCardDataManager class is responsible for reading destiny card data from a CSV file and providing it as an ArrayList of String arrays.
 */
public class DestinyCardDataManager implements DataManager{

    private static final int FIELD_NUM = 4;

    /**
     * Creates destiny cards based on the information in the specified CSV file.
     *
     * @param fileName The name of the CSV file containing destiny card information.
     * @return An ArrayList of String arrays representing the data read from the CSV file.
     */
    public ArrayList<String[]> readData(String fileName){

        ArrayList<String[]> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null){
                String[] values = line.split(DELIMITER);
                if (values.length < FIELD_NUM) {
                    // Handle the case where there are missing values in the CSV line
                    throw new IllegalArgumentException("Invalid data in the CSV file: " + line);
                }
                lines.add(values);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The specified file '" + fileName + "' does not exist or cannot be found.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + e.getMessage());
        }

        return lines;
    }
}
