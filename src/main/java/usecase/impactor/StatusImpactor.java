package usecase.impactor;

import entity.Player;

import java.util.ArrayList;

/**
 * It is an impactor impacting the status attribute of the player
 */
public class StatusImpactor {

    public void addStatus(Player player, String status){
        player.addStatus(status);
    }

    public void removeStatus(Player player, String status){
        player.removeStatus(status);
    }
}
