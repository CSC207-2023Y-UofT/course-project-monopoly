package entities;

/**
 *  Represents the starting point block on the game map.
 */
public class StartingPoint extends Block {
    private static int bonus;   // Bonus amount provided by the starting point.

    /**
     * Class constructor to create a new StartingPoint block with the specified ID and bonus amount.
     *
     * @param ID    The unique identifier for the StartingPoint block.
     * @param bonus The bonus amount provided by the starting point.
     */
    public StartingPoint(int ID, int bonus) {
        super(ID);
        StartingPoint.bonus = bonus;
    }

    /**
     * Gets the name of the block, which is "startingpoint" for this type.
     *
     * @return The name of the block.
     */
    public String getBlockName()
    {
        return "startingpoint";
    }

    /**
     * Gets the bonus amount provided by the starting point.
     *
     * @return The bonus amount provided by the starting point.
     */
    public static int getBonus() {
        return bonus;
    }

    /**
     * Sets the bonus amount provided by the starting point.
     *
     * @param bonus The bonus amount to set for the starting point.
     */
    public static void setBonus(int bonus) {
        StartingPoint.bonus = bonus;
    }

    /**
     * This method is not used for the StartingPoint block.
     * StartingPoint block does not have any specific actions to perform when executed in the game loop.
     *
     * @param data The data structure representing the game state.
     */
    @Override
    public void run(GameData data) {}
}
