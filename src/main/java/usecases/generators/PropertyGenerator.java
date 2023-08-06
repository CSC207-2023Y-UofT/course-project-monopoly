package usecases.generators;

import entities.Property;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The PropertyGenerator class represents an use case responsible for generating property blocks.
 * It reads property information from a CSV file and creates a list of properties based on the data.
 */
public class PropertyGenerator{

    /**
     * The delimiter used to separate values in the CSV file.
     */
    static final String DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    /**
     * Creates properties based on the information in the specified CSV file.
     *
     * @param fileName The name of the CSV file containing property information.
     * @return An ArrayList of Property objects generated from the CSV data.
     * @throws IllegalArgumentException If there are missing values in the CSV line.
     * @throws NumberFormatException    If action values are not in the expected numeric format in the CSV file.
     */
    public static ArrayList<Property> generateProperties(String fileName){

        ArrayList<entities.Property> propertyList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            ArrayList<Integer> priceList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
            ArrayList<Integer> taxList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);

                if (values.length < 12) {
                    // Handle the case where there are missing values in the CSV line
                    throw new IllegalArgumentException("Invalid data in the CSV file: " + line);
                }

                try {
                    // Generate the priceList, length 6 with last element being 0.
                    for (int i = 0; i < 5; i++) {
                        priceList.set(i, Integer.parseInt(values[i + 2]));
                    }

                    // Generate the taxList, length 6 with first element being 0.
                    for (int i = 1; i < 6; i++) {
                        taxList.set(i, Integer.parseInt(values[i + 6]));
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where action values are not in the expected numeric format
                    throw new NumberFormatException("Invalid action value in the CSV file: " + line);
                }


                Property property = new Property(Integer.parseInt(values[0]), values[1], priceList, taxList);
                propertyList.add(property);
            }

        } catch (FileNotFoundException e) {
            System.err.println("The specified file '" + fileName + "' does not exist or cannot be found.");
        } catch (IOException e){
            e.printStackTrace();
        }

        return propertyList;
    }
}
