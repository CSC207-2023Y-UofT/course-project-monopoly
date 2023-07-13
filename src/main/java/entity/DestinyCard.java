package entity;

import java.util.ArrayList;

/**
 * Represents the destiny card
 */
public class DestinyCard {

    private String message;

    /**
     * There are three elements in the arraylist:
     * 1st (Integer): represents the value of change of the money
     * 2nd (Integer): represents the value of change of the position or the id of the target block
     * 3rd (String): represents the statuses
     */
    private ArrayList<Object> actions;

    public DestinyCard(String message, ArrayList<Object> actions) {
        this.message = message;
        this.actions = actions;
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
