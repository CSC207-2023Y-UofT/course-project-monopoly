package usecases;

import entities.Destiny;
import entities.DestinyCard;

import java.util.ArrayList;
import java.util.Random;

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

        if (cardPool.isEmpty()) {
            return null;
        }

        int index = new Random().nextInt(cardPool.size());
        return cardPool.get(index);
    }
}
