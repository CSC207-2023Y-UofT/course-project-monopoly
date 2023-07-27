package entity;

import java.util.ArrayList;

/**
 * Represents the destiny card
 */
public class DestinyCard {

    private final String message;

    /**
     * There are three elements in the arraylist:
     * 1st (Integer): represents the value of change of the money
     * 2nd (Integer): represents the value of change of the position or the id of the target block
     * 3rd (Integer): represents the rounds the player should stop
     */
    private final ArrayList<Object> actions;

    public DestinyCard(String message, ArrayList<Object> actions) {
        this.message = message;
        this.actions = actions;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Object> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "DestinyCard{" +
                "message='" + message + '\'' +
                ", actions= " + "money: " + actions.get(0) + " position: "
                + actions.get(1) + " statuses: " + actions.get(2) +
                '}';
    }
}
