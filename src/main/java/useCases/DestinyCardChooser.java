package useCases;

import entity.Destiny;
import entity.DestinyCard;

import java.util.ArrayList;

/**
 * A class for choosing destiny cards from the destiny block.
 */
public class DestinyCardChooser {

    /**
     * Randomly selects a destiny card from the given destiny block.
     *
     * @param destiny The Destiny block containing the pool of destiny cards.
     * @return A randomly chosen DestinyCard from the pool, or null if the card pool is empty.
     */
    public static DestinyCard chooseCard(Destiny destiny){
        ArrayList<DestinyCard> cardPool = destiny.getDestinyCardPool();

        try {
            int index = (int) (Math.random() * cardPool.size());
            return cardPool.get(index);
        } catch (IndexOutOfBoundsException e){
            // Handle the case where the card pool is empty or has an invalid index.
            // For simplicity, we return null to indicate no valid card was chosen.
            return null;
        }
    }
}
