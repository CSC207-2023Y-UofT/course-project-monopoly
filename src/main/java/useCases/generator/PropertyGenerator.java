package useCases.generator;

import entity.Property;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * It is a generator generating the property blocks
 */
public class PropertyGenerator {

    static final String DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    /**
     * Create properties based on the information file
     */

    public static ArrayList<Property> generateProperties(String fileName){

        ArrayList<Property> propertyList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            ArrayList<Integer> priceList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
            ArrayList<Integer> taxList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                System.out.println(Arrays.toString(values));

                // generate the priceList, length 6 with last element being 0.
                for (int i = 0; i < 5; i++) {
                    priceList.set(i, Integer.parseInt(values[i + 2]));
                }

                // generate the taxList, length 6 with first element being 0.
                for (int i = 1; i < 6; i++) {
                    taxList.set(i, Integer.parseInt(values[i + 6]));
                }

                Property property = new Property(Integer.parseInt(values[0]), values[1], priceList, taxList);
                propertyList.add(property);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return propertyList;
    }
}
