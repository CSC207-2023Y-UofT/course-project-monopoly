package usecase;

import entity.Player;

/**
 * The class used to check the status of the player
 */
public class StatusChecker {
    public static boolean isPlayable(Player player)
    {
        int status = player.getStatus().get("playable");
        return status >= 0;
    }
    public static boolean isMovable(Player player)
    {
        int status = player.getStatus().get("movable");
        return status >= 0;
    }
}
