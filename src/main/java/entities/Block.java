package entities;

/**
 * Represents a block on the game map.
 * This is an abstract class that serves as a base for different types of blocks in the Monopoly-like game.
 */
public abstract class Block {
    protected int id;

    /**
     * Constructs a new Block with the specified ID.
     *
     * @param ID The unique identifier for the block.
     */
    public Block(int ID){
        this.id = ID;
    }

    /**
     * Gets the ID of the block.
     *
     * @return The ID of the block.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the block.
     *
     * @param id The ID to be set for the block.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the block.
     * Subclasses should override this method to provide specific names for each block type.
     *
     * @return The name of the block, or null if not applicable.
     */
    public String getBlockName()
    {
        return null;
    }

    /**
     * Executes the corresponding actions associated with the block.
     * Subclasses should implement this method to define specific behavior for each block type.
     *
     * @param data The data structure representing the game state.
     */
    public abstract void run(GameData data);


}
