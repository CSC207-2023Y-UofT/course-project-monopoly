package entity;

import java.util.ArrayList;

/**
 * Represents the destiny block
 */
public class Destiny extends Block {
    private ArrayList<DestinyCard> destinyCardPool;

    public Destiny(int ID) {
        super(ID);
    }

    public ArrayList<DestinyCard> getDestinyCardPool() {
        return destinyCardPool;
    }

    public void setDestinyCardPool(ArrayList<DestinyCard> destinyCardPool) {
        this.destinyCardPool = destinyCardPool;
    }
}
