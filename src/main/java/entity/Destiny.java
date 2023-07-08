package entity;

import java.util.ArrayList;

/**
 * Represents the destiny block
 */
public class Destiny extends Block {
    ArrayList<DestinyCard> destinyCardPool;

    public Destiny(int ID, ArrayList<DestinyCard> destinyCardPool) {
        super(ID);
        this.destinyCardPool = destinyCardPool;
    }
}
