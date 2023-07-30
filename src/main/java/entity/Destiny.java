package entity;

import java.util.ArrayList;

/**
 * Represents the destiny block.
 * The destiny block contains a pool of destiny cards used in the game to determine various outcomes and events.
 * This class extends the Block class and defines additional functionality for destiny-related operations.
 */
public class Destiny extends Block {

    private final ArrayList<DestinyCard> destinyCardPool;
    /**
     * Constructor to create a Destiny block with the given ID.
     *
     * @param ID The unique identifier for the Destiny block.
     */
    public Destiny(int ID) {
        super(ID);
        this.destinyCardPool = new ArrayList<>();
    }

    /**
     * Returns the name of the Destiny block.
     * This method overrides the getBlockName() method from the Block class.
     *
     * @return The name of the Destiny block ("destiny").
     */
    @Override
    public String getBlockName() {
        return "destiny";
    }

    /**
     * Retrieves the pool of destiny cards associated with this Destiny block.
     *
     * @return An ArrayList containing the destiny cards in the pool.
     */
    public ArrayList<DestinyCard> getDestinyCardPool() {
        return destinyCardPool;
    }

    /**
     * Adds a collection of destiny cards to the destiny card pool.
     *
     * @param destinyCardPool The ArrayList of DestinyCard objects to add to the pool.
     */
    public void addDestinyCardPool(ArrayList<DestinyCard> destinyCardPool) {
        this.destinyCardPool.addAll(destinyCardPool);
    }
}
