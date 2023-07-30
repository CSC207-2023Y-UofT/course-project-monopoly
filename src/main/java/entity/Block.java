package entity;

/**
 * Represents the blocks of the map
 */
public abstract class Block {
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

    public String getBlockName()
    {
        return null;
    }

    /**
     * Runs the corresponding usecase(s) to avoid switch case in the
     * main loop
     * @param data data structure for the game
     */
    public abstract void run(GameData data);

}
