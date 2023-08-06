package usecases.impactors;

import entities.Player;

import java.util.HashMap;

/**
 * The StatusImpactor class represents an impactor that affects the status attribute of a player in the game.
 * It provides methods to initialize, change, and automatically update the status of a player.
 */
public class StatusImpactor {

    /**
     * Initializes the status of the player with the initial values.
     *
     * @param player The player whose status is to be initialized.
     *              For "playable": 0 for true and -1 for false.
     *              For "movable": 0 for true and a negative number for rounds that the player cannot move.
     */
    public static void initStatus(Player player){
        HashMap<String, Integer> initialStatus = new HashMap<>();
        initialStatus.put("movable", 0);
        initialStatus.put("playable", 0);
        player.setStatus(initialStatus);
    }

    /**
     * Changes the specified status of the player to the given value.
     *
     * @param player The player whose status is to be changed.
     * @param name   The name of the status attribute to change (e.g., "playable" or "movable").
     * @param status The new value for the status attribute.
     */
    public static void changeStatus(Player player, String name, Integer status) {
        HashMap<String, Integer> original = player.getStatus();
        original.replace(name,status);
        player.setStatus(original);

    }

    /**
     * Automatically updates the "movable" status of the player.
     * Used for counting the rounds in the exam center where the player cannot move for a certain number of rounds.
     *
     * @param player The player whose "movable" status is to be updated.
     */
    public static void changeStatus(Player player) {
        HashMap<String, Integer> original = player.getStatus();
        if(original.get("movable") <0)
        {
            int value = original.get("movable");
            value += 1;
            original.replace("movable", value);
        }
    }
}
