package entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the player of the game
 */
public class Player {

    private final int userId;
    private int money;
    private int position; // The ID of the block that this player stands on.
    private ArrayList<Property> properties;
    private HashMap<String, Integer> status;

    /**
     * Class constructor to create a new player with the specified user ID and initial money.
     *
     * @param userId The unique ID of the player.
     * @param money  The initial amount of money the player starts with.
     */
    public Player(int userId, int money){
        this.userId = userId;
        this.money = money;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.status = new HashMap<>();
    }

    /**
     * Gets the user ID of the player.
     *
     * @return The user ID of the player.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the amount of money the player has.
     *
     * @return The amount of money the player has.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the amount of money for the player.
     *
     * @param money The amount of money to set for the player.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets the position ID of the block that the player stands on.
     *
     * @return The position ID of the player.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the position ID of the block that the player stands on.
     *
     * @param position The position ID to set for the player.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Gets the list of properties owned by the player.
     *
     * @return The list of properties owned by the player.
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Sets the list of properties owned by the player.
     *
     * @param properties The list of properties to set for the player.
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    /**
     * Gets the current status of the player as a mapping of status names to their values.
     *
     * @return The status of the player.
     */
    public HashMap<String, Integer> getStatus() {
        return status;
    }

    /**
     * Sets the current status of the player as a mapping of status names to their values.
     *
     * @param status The status to set for the player.
     */
    public void setStatus(HashMap<String, Integer> status){this.status = status;}

    /**
     * Checks if the player is equal to another player based on their user IDs.
     *
     * @param player The player to compare.
     * @return true if the player has the same user ID, false otherwise.
     */
    public boolean equals(Player player){
        return this.userId == player.getUserId();
    }

    /**
     * Returns a string representation of the player, showing their user ID, money, properties, and status.
     *
     * @return A string representation of the player.
     */
    @Override
    public String toString() {
        return "Player{" +
                "userId=" + userId +
                ", money=" + money +
                ", properties=" + properties +
                ", status=" + status +
                '}';
    }
}
