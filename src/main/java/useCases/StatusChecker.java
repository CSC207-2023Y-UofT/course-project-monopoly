package useCases;

import entity.Player;

/**
 * The class used to check the status of the player
 */
public class StatusChecker {

    public static boolean isPlayable(Player player)
    {
        /**
         * check if this player can play in this game
         * @param  Player player
         * @return a boolean value represents if he/she can stay in the game
         */
        int status = player.getStatus().get("playable");
        return status >= 0;
    }
    public static boolean isMovable(Player player)
    {
        /**
         * check if this player can move in his/her next round
         * @param  Player player
         * @return a boolean value represents if he/she can play in his/her next round
         */
        int status = player.getStatus().get("movable");
        return status >= 0;
    }
}
