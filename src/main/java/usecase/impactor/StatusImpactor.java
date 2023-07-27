package usecase.impactor;

import entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * It is an impactor impacting the status attribute of the player
 */
public class StatusImpactor {

    public static void initStatus(Player player){
        if(player.getStatus() == null) {
            /*
             for playable : 0 for true and -1 for false
             for movable:  0 for true and negative number for rounds that the player cannot move*/
            HashMap<String, Integer> initialStatus = new HashMap<>();
            initialStatus.put("movable", 0);
            initialStatus.put("playable", 0);
            player.setStatus(initialStatus);
        }
        }
    public static void changeStatus(Player player, String name, Integer status)
    {
        /*
        use this method to change the Status of the player due to different event
         * */

        HashMap<String, Integer> original = player.getStatus();
        original.replace(name,status);
        player.setStatus(original);

    }
    public static void changeStatus(Player player)
    {
        // use this method to change the Status of the player automatically
        // (for counting the rounds in exam center)
        HashMap<String, Integer> original = player.getStatus();
        if(original.get("movable") <0)
        {
            int value = original.get("movable");
            value += 1;
            original.replace("movable", value);
        }
    }
}
