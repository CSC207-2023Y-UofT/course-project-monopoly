package entity;

import java.util.ArrayList;

/**
 * Represents the destiny card
 */
public class DestinyCard {

    String message;
    ArrayList<Object> actions;

    public DestinyCard(String message, ArrayList<Object> actions) {
        this.message = message;
        this.actions = actions;
    }
}
