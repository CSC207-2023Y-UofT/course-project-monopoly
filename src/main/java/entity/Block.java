package entity;

/**
 * Represents the blocks of the map
 */
public class Block {
    protected int id;

    /**
     * Class constructor
     * @param ID
     */
    public Block(int ID){
        this.id = ID;
    }

    /**
     * Gets the id of the block
     * @return id of the block
     */
    public int getId() {
        return id;
    }

    /**
     * Changes the id of the block
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

}
