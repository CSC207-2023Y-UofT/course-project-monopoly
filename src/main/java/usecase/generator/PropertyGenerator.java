package usecase.generator;

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

    static final String DELIMITER = ",";

    /**
     * Create properties based on the information file
     */
    public static ArrayList<Property> generateProperties(String fileName){
        ArrayList<Property> propertyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            ArrayList<Integer> priceList = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
            ArrayList<Integer> taxList = new ArrayList<>(Arrays.asList(0, 0, 0, 0));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);

                // generate the priceList
                priceList.set(0, Integer.parseInt(values[2]));
                priceList.set(1, Integer.parseInt(values[3]));
                priceList.set(2, Integer.parseInt(values[4]));

                // generate the taxList
                taxList.set(1, Integer.parseInt(values[5]));
                taxList.set(2, Integer.parseInt(values[6]));
                taxList.set(3, Integer.parseInt(values[7]));

                Property property = new Property(Integer.parseInt(values[0]), values[1], priceList, taxList);
                propertyList.add(property);
                System.out.println(property);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return propertyList;
    }
}
