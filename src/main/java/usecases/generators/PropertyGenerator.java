package usecases.generators;

import datagateways.PropertyDataManager;
import entities.Property;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The PropertyGenerator class represents a use case responsible for generating property blocks.
 * It reads property information from a CSV file and creates a list of properties based on the data.
 */
public class PropertyGenerator{

    /**
     * Generates a list of properties based on information from a CSV file.
     *
     * @param fileName The name of the CSV file containing property information.
     * @return An ArrayList of Property objects generated from the CSV data.
     * @throws NumberFormatException    If action values in the CSV file are not in the expected numeric format.
     * @throws IllegalArgumentException If there are missing values in the CSV line.
     */
    public static ArrayList<Property> generateProperties(String fileName){
        PropertyDataManager dataManager = new PropertyDataManager();
        ArrayList<String[]> data = dataManager.readData(fileName);

        ArrayList<Property> propertyList = new ArrayList<>();
        ArrayList<Integer> priceList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        ArrayList<Integer> taxList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

        // The values array contains the following elements:
        //   values[0]: Represents the ID of the property.
        //   values[1]: Represents the name of the property.
        //   values[2]-values[6]: Represents the prices for 0-4 level respectively
        //   values[7]-values[11]: Represents the texes for 1-5 level respectively
        for(String[] values: data){
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
                throw new NumberFormatException("Invalid action value in the CSV file: " + Arrays.toString(values));
            }
            Property property = new Property(Integer.parseInt(values[0]), values[1], priceList, taxList);
            propertyList.add(property);
        }
        return propertyList;
    }
}

