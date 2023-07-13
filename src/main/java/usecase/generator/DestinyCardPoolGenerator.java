package usecase.generator;

import entity.Destiny;
import entity.DestinyCard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * It is a generator generating the destiny card pool of the destiny block
 */
public class DestinyCardPoolGenerator {
    // use for csv splitting to keep the comma in the content
    static final String DELIMITER = ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
    /**
     * Generate the destiny card pool based on the information file
     */
    public static void generateDestinyCardPool(String fileName, Destiny destiny){
        ArrayList<DestinyCard> cardPool = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))){
            String line;
            ArrayList<Object> actions = new ArrayList<>(Arrays.asList(0, 0, ""));
            while ((line = br.readLine()) != null){
                String[] values = line.split(DELIMITER);

                // generate the actions
                actions.set(0, Integer.parseInt(values[1]));
                actions.set(1, Integer.parseInt(values[2]));
                actions.set(2, values[3]);

                // remove the "" in the content
                String message = values[0].replace("\"", "");

                DestinyCard card = new DestinyCard(message, actions);
                cardPool.add(card);
            }

            destiny.setDestinyCardPool(cardPool);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
