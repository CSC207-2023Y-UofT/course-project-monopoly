package useCases;

import entity.Destiny;
import entity.DestinyCard;

import java.util.ArrayList;


public class DestinyCardChooser {
    public static DestinyCard chooseCard(Destiny destiny){
        ArrayList<DestinyCard> cardPool = destiny.getDestinyCardPool();
        int index = (int)(Math.random() * cardPool.size());

        return cardPool.get(index);
    }
}
