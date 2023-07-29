package entity;

import java.util.ArrayList;

/**
 * Represents a destiny card in the game.
 * Destiny cards are used to determine various outcomes and events during the game.
 */
public class DestinyCard {
    /**
     * The message or content of the destiny card.
     */
    private final String message;

    /**
     * The actions associated with the destiny card.
     * The ArrayList contains three elements:
     * 1. (Integer) Represents the value of the money change when the card is played.
     * 2. (Integer) Represents the value of the change of position or the ID of the target block when the card is played.
     * 3. (Integer) Represents the number of rounds the player should stop when the card is played.
     */
    private final ArrayList<Object> actions;

    /**
     * Constructs a new DestinyCard with the given message and actions.
     *
     * @param message The message or content of the destiny card.
     * @param actions The actions associated with the destiny card.
     */
    public DestinyCard(String message, ArrayList<Object> actions) {
        this.message = message;
        this.actions = actions;
    }

    /**
     * Gets the message or content of the destiny card.
     *
     * @return The message of the destiny card.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the actions associated with the destiny card.
     * The ArrayList contains three elements:
     * 1. (Integer) Represents the value of the money change when the card is played.
     * 2. (Integer) Represents the value of the change of position or the ID of the target block when the card is played.
     * 3. (Integer) Represents the number of rounds the player should stop when the card is played.
     *
     * @return The ArrayList of actions associated with the destiny card.
     */
    public ArrayList<Object> getActions() {
        return actions;
    }

    /**
     * Returns a string representation of the destiny card.
     *
     * @return A string containing the message and actions of the destiny card.
     */
    @Override
    public String toString() {
        return "DestinyCard{" +
                "message='" + message + '\'' +
                ", actions= " + "money: " + actions.get(0) + " position: " + actions.get(1) + " rounds: " + actions.get(2) +
                '}';
    }
}
