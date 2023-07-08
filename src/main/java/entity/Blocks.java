package entity;

/**
 * Represents the blocks of the map
 */
public class Blocks {
    public int id;

    /**
     * Class constructor
     * @param ID
     */
    public Blocks(int ID){
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

    @Override
    public String toString() {
        return "Blocks{" +
                "id=" + id +
                '}';
    }
}
