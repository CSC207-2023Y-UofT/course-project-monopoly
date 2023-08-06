package usecases;

import entities.Player;

/**
 * The StatusChecker class is used to check the status of a player in the game.
 * It provides methods to determine if a player can continue playing in the game and if the player can move in their next round.
 */
public class StatusChecker {

    /**
     * The StatusChecker class is used to check the status of a player in the game.
     * It provides methods to determine if a player can continue playing in the game and if the player can move in their next round.
     */
    public static boolean isPlayable(Player player) {
        int status = player.getStatus().get("playable");
        return status >= 0;
    }

    /**
     * The StatusChecker class is used to check the status of a player in the game.
     * It provides methods to determine if a player can continue playing in the game and if the player can move in their next round.
     */
    public static boolean isMovable(Player player) {
        int status = player.getStatus().get("movable");
        return status >= 0;
    }
}
